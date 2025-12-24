@file:OptIn(org.skepsun.kototoro.parsers.InternalParsersApi::class)

package org.skepsun.kototoro.parsers.site.zh

import okhttp3.Headers
import org.jsoup.nodes.Document
import org.jsoup.nodes.Element
import org.skepsun.kototoro.parsers.InternalParsersApi
import org.skepsun.kototoro.parsers.MangaLoaderContext
import org.skepsun.kototoro.parsers.MangaSourceParser
import org.skepsun.kototoro.parsers.core.PagedMangaParser
import org.skepsun.kototoro.parsers.model.ContentRating
import org.skepsun.kototoro.parsers.model.Manga
import org.skepsun.kototoro.parsers.model.MangaChapter
import org.skepsun.kototoro.parsers.model.MangaListFilter
import org.skepsun.kototoro.parsers.model.MangaListFilterCapabilities
import org.skepsun.kototoro.parsers.model.MangaListFilterOptions
import org.skepsun.kototoro.parsers.model.MangaPage
import org.skepsun.kototoro.parsers.model.MangaParserSource
import org.skepsun.kototoro.parsers.model.MangaSource
import org.skepsun.kototoro.parsers.model.MangaState
import org.skepsun.kototoro.parsers.model.MangaTag
import org.skepsun.kototoro.parsers.model.MangaTagGroup
import org.skepsun.kototoro.parsers.model.SortOrder
import org.skepsun.kototoro.parsers.network.UserAgents
import org.skepsun.kototoro.parsers.config.ConfigKey
import org.skepsun.kototoro.parsers.util.generateUid
import org.skepsun.kototoro.parsers.util.parseHtml
import org.skepsun.kototoro.parsers.util.urlEncoded
import java.util.EnumSet

/**
 * 百合会（baihehui / yamibo）
 * 参考 venera-configs/baihehui.js
 */
@MangaSourceParser("BAIHEHUI", "百合会", "zh")
internal class Baihehui(context: MangaLoaderContext) :
    PagedMangaParser(context, MangaParserSource.BAIHEHUI, pageSize = 50) {

    override val configKeyDomain = org.skepsun.kototoro.parsers.config.ConfigKey.Domain("www.yamibo.com")

    private val ua = UserAgents.CHROME_DESKTOP

    private val categoryTypes: Map<String, FilterEntry> = mapOf(
        "全部作品" to FilterEntry("manga/list", 'a', "?"),
        "原创" to FilterEntry("manga/list?q=4", 'a', "&"),
        "同人" to FilterEntry("manga/list?q=6", 'a', "&"),
    )
    private val articleTypes: Map<String, FilterEntry> = mapOf(
        "翻页漫画" to FilterEntry("search/type?type=3&tag=", 'b', "翻页漫画"),
        "条漫" to FilterEntry("search/type?type=3&tag=", 'b', "条漫"),
        "四格" to FilterEntry("search/type?type=3&tag=", 'b', "四格"),
        "绘本" to FilterEntry("search/type?type=3&tag=", 'b', "绘本"),
        "杂志" to FilterEntry("search/type?type=3&tag=", 'b', "杂志"),
        "合志" to FilterEntry("search/type?type=3&tag=", 'b', "合志"),
    )
    private val relateTypes: Map<String, FilterEntry> = mapOf(
        "编辑推荐" to FilterEntry("manga/rcmds?type=3012", 'c', "&"),
        "最近更新" to FilterEntry("manga/latest", 'c', "?"),
        "原创推荐" to FilterEntry("manga/rcmds?type=3014", 'c', "&"),
        "同人推荐" to FilterEntry("manga/rcmds?type=3015", 'c', "&"),
    )

    override val availableSortOrders: Set<SortOrder> =
        EnumSet.of(SortOrder.UPDATED)

    override val filterCapabilities: MangaListFilterCapabilities
        get() = MangaListFilterCapabilities(
            isSearchSupported = true,
            isSearchWithFiltersSupported = true,
            isMultipleTagsSupported = false,
            isTagsExclusionSupported = false,
        )

    override suspend fun getFilterOptions(): MangaListFilterOptions {
        val tags = buildList {
            addAll(categoryTypes.map { (title, entry) -> entry.toTag(title, source) })
            addAll(articleTypes.map { (title, entry) -> entry.toTag(title, source) })
            addAll(relateTypes.map { (title, entry) -> entry.toTag(title, source) })
        }.toSet()
        val groups = listOf(
            MangaTagGroup("分类", categoryTypes.map { (title, entry) -> entry.toTag(title, source) }.toSet()),
            MangaTagGroup("作品类型（需要登陆）", articleTypes.map { (title, entry) -> entry.toTag(title, source) }.toSet()),
            MangaTagGroup("更多推荐", relateTypes.map { (title, entry) -> entry.toTag(title, source) }.toSet()),
        )
        return MangaListFilterOptions(
            availableTags = tags,
            tagGroups = groups,
            availableStates = EnumSet.of(MangaState.ONGOING, MangaState.FINISHED),
            availableContentRating = EnumSet.of(ContentRating.SAFE, ContentRating.SUGGESTIVE, ContentRating.ADULT),
        )
    }

    override fun getRequestHeaders(): Headers = Headers.Builder()
        .add("User-Agent", ua)
        .add("Referer", "https://${domain}/")
        .build()

    override suspend fun getListPage(page: Int, order: SortOrder, filter: MangaListFilter): List<Manga> {
        // Search takes precedence
        if (!filter.query.isNullOrEmpty()) {
            return search(filter.query!!, page)
        }

        val tag = filter.tags.firstOrNull()
        val entry = tag?.key?.let { FilterEntry.fromKey(it) } ?: categoryTypes["全部作品"]
        return if (entry != null) {
            loadCategory(entry, page)
        } else {
            emptyList()
        }
    }

    private fun padCoverId(id: String): String = id.padStart(3, '0')

    private fun buildCoverUrl(id: String): String = "https://${domain}/coverm/000/000/${padCoverId(id)}.jpg"

    private fun parseListRows(doc: Document, tagExtractor: (Element) -> List<String>): List<Manga> {
        val rows = doc.select("tr[data-key]")
        return rows.mapNotNull { row ->
            val href = row.selectFirst("a")?.attr("href") ?: return@mapNotNull null
            val id = href.substringAfterLast("/manga/").takeWhile { it.isDigit() }.ifEmpty { return@mapNotNull null }
            val title = row.selectFirst("a")?.text()?.trim().orEmpty()
            val author = row.select("td").getOrNull(2)?.text()?.trim().orEmpty()
            val tags = tagExtractor(row).filter { it.isNotBlank() }.map { MangaTag(it, it, source) }.toSet()
            val desc = row.select("td").lastOrNull()?.text()?.trim().orEmpty()
            Manga(
                id = generateUid(id),
                url = id,
                publicUrl = "https://${domain}/manga/$id",
                coverUrl = buildCoverUrl(id),
                title = title,
                altTitles = emptySet(),
                rating = org.skepsun.kototoro.parsers.model.RATING_UNKNOWN,
                tags = tags,
                authors = if (author.isNotEmpty()) setOf(author) else emptySet(),
                state = null,
                source = source,
                contentRating = null,
                description = if (desc.isNotEmpty()) desc else null,
            )
        }
    }

    private suspend fun loadCategory(entry: FilterEntry, page: Int): List<Manga> {
        val url = when (entry.type) {
            'a' -> "https://${domain}/${entry.path}${entry.suffix}sort=updated_at&page=$page&per-page=$pageSize"
            'b' -> "https://${domain}/${entry.path}${entry.suffix.urlEncoded()}&sort=updated_at&page=$page&per-page=$pageSize"
            else -> "https://${domain}/${entry.path}${entry.suffix}sort=updated_at&page=$page&per-page=$pageSize"
        }
        val resp = webClient.httpGet(url, getRequestHeaders())
        if (!resp.isSuccessful) return emptyList()
        val doc = resp.parseHtml()
        return when (entry.type) {
            'a' -> parseListRows(doc) { row ->
                val tds = row.select("td")
                listOfNotNull(
                    tds.getOrNull(4)?.text()?.trim(),
                    tds.getOrNull(5)?.text()?.trim(),
                )
            }
            'b' -> parseListRows(doc) { row ->
                val tds = row.select("td")
                listOfNotNull(
                    tds.getOrNull(3)?.text()?.replace("[", "")?.replace("]", "")?.trim(),
                    tds.getOrNull(4)?.text()?.trim(),
                )
            }
            else -> parseListRows(doc) { emptyList() }
        }
    }

    private suspend fun search(query: String, page: Int): List<Manga> {
        val url = "https://${domain}/search/manga?SearchForm%5Bkeyword%5D=${query.urlEncoded()}&page=$page"
        val resp = webClient.httpGet(url, getRequestHeaders())
        if (!resp.isSuccessful) return emptyList()
        val doc = resp.parseHtml()
        return parseListRows(doc) { emptyList() }
    }

    override suspend fun getDetails(manga: Manga): Manga {
        val resp = webClient.httpGet("https://${domain}/manga/${manga.url}", getRequestHeaders())
        if (!resp.isSuccessful) return manga
        val doc = resp.parseHtml()

        val title = doc.selectFirst("h3.col-md-12")?.text()?.trim().orEmpty().ifEmpty { manga.title }
        val author = doc.select("p").firstOrNull { it.text().contains("作者：") }?.text()
            ?.replace("作者：", "")?.trim().orEmpty()
        val update = doc.select("p").firstOrNull { it.text().contains("更新时间：") }?.text()
            ?.replace("更新时间：", "")?.trim().orEmpty()
        val tags = doc.select("a.label.label-ntype").mapNotNull { it.text().trim() }.filter { it.isNotEmpty() }

        val chapters = doc.select("div[data-key]").mapIndexed { index, el ->
            val key = el.attr("data-key")
            val name = el.selectFirst("a")?.text()?.trim().orEmpty().ifEmpty { "Chapter $key" }
            MangaChapter(
                id = generateUid("$key-${manga.id}"),
                title = name,
                number = (index + 1).toFloat(),
                volume = 0,
                url = key,
                scanlator = null,
                uploadDate = 0,
                branch = null,
                source = source,
            )
        }

        return manga.copy(
            title = title,
            authors = if (author.isNotEmpty()) setOf(author) else manga.authors,
            tags = if (tags.isNotEmpty()) tags.map { MangaTag(it, it, source) }.toSet() else manga.tags,
            chapters = chapters,
            description = manga.description,
            contentRating = manga.contentRating ?: ContentRating.SAFE,
        )
    }

    override suspend fun getPages(chapter: MangaChapter): List<MangaPage> {
        val baseUrl = "https://${domain}/manga/view-chapter?id=${chapter.url}"
        val pages = mutableListOf<MangaPage>()

        suspend fun loadPage(pageIndex: Int): Pair<String?, Int?> {
            val url = "$baseUrl&page=$pageIndex"
            val resp = webClient.httpGet(url, getRequestHeaders())
            if (!resp.isSuccessful) return null to null
            val doc = resp.parseHtml()
            val img = doc.selectFirst("img#imgPic")?.attr("src")
            val max = doc.selectFirst("li.last > a")?.attr("data-page")?.toIntOrNull()?.plus(1)
            return img to max
        }

        val (firstImg, maxPage) = loadPage(1)
        if (firstImg != null) {
            pages.add(MangaPage(id = generateUid("$firstImg-0"), url = firstImg, preview = firstImg, source = source))
        }
        val total = maxPage ?: 1
        if (total > 1) {
            for (i in 2..total) {
                val (img, _) = loadPage(i)
                if (img != null) {
                    pages.add(MangaPage(id = generateUid("$img-$i"), url = img, preview = img, source = source))
                }
            }
        }
        return pages
    }

    override suspend fun getPageUrl(page: MangaPage): String = page.url

    private data class FilterEntry(
        val path: String,
        val type: Char,
        val suffix: String,
    ) {
        fun toTag(title: String, source: MangaSource): MangaTag =
            MangaTag(title = title, key = toKey(), source = source)

        fun toKey(): String = "$path@$type@$suffix"

        companion object {
            fun fromKey(key: String): FilterEntry? {
                val parts = key.split("@")
                if (parts.size < 3) return null
                val typeChar = parts[1].firstOrNull() ?: return null
                val suffix = parts.subList(2, parts.size).joinToString("@")
                return FilterEntry(parts[0], typeChar, suffix)
            }
        }
    }
}
