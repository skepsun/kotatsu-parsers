@file:OptIn(org.skepsun.kototoro.parsers.InternalParsersApi::class)

package org.skepsun.kototoro.parsers.site.zh

import okhttp3.Headers
import org.jsoup.nodes.Document
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
import org.skepsun.kototoro.parsers.model.MangaTag
import org.skepsun.kototoro.parsers.model.SortOrder
import org.skepsun.kototoro.parsers.network.UserAgents
import org.skepsun.kototoro.parsers.util.generateUid
import org.skepsun.kototoro.parsers.util.parseHtml
import org.skepsun.kototoro.parsers.util.urlEncoded
import java.util.EnumSet

/**
 * 优酷漫画 (ykmh.net)
 */
@MangaSourceParser("YKMH", "优酷漫画", "zh")
internal class YkmhParser(context: MangaLoaderContext) :
    PagedMangaParser(context, MangaParserSource.YKMH, pageSize = 20) {

    override val configKeyDomain = org.skepsun.kototoro.parsers.config.ConfigKey.Domain("www.ykmh.net")
    override val availableSortOrders: Set<SortOrder> = EnumSet.of(SortOrder.NEWEST)

    override val filterCapabilities: MangaListFilterCapabilities
        get() = MangaListFilterCapabilities(
            isSearchSupported = true,
            isSearchWithFiltersSupported = false,
        )

    override suspend fun getFilterOptions(): MangaListFilterOptions =
        MangaListFilterOptions(
            availableTags = emptySet(),
            availableContentRating = EnumSet.of(ContentRating.SAFE, ContentRating.SUGGESTIVE, ContentRating.ADULT),
        )

    override fun getRequestHeaders(): Headers = Headers.Builder()
        .add("User-Agent", UserAgents.CHROME_DESKTOP)
        .add("Referer", "https://${domain}/")
        .build()

    private fun baseUrl(): String = "https://${domain}"

    override suspend fun getListPage(page: Int, order: SortOrder, filter: MangaListFilter): List<Manga> {
        // No paginated listing in JS; fallback to home (page 1) or search
        if (!filter.query.isNullOrEmpty()) {
            return search(filter.query!!, page)
        }
        val resp = webClient.httpGet(baseUrl(), getRequestHeaders())
        if (!resp.isSuccessful) return emptyList()
        val doc = resp.parseHtml()
        return parseLatest(doc)
    }

    private suspend fun search(keyword: String, page: Int): List<Manga> {
        if (page > 1) return emptyList()
        val resp = webClient.httpGet("${baseUrl()}/search?q=${keyword.urlEncoded()}", getRequestHeaders())
        if (!resp.isSuccessful) return emptyList()
        val doc = resp.parseHtml()
        return parseList(doc.select("div.ebook-seealso div.ebook-ol > li"))
    }

    private fun parseLatest(doc: Document): List<Manga> {
        val items = doc.select(".ebooks ul.list-unstyled > li")
        if (items.isNotEmpty()) {
            return parseList(items)
        }
        // Fallback to carousel keywords if no items
        val list = mutableListOf<Manga>()
        val regex = Regex("<li data-key=\"(\\d+)\"><a href=\"(https://www\\.ykmh\\.net/manhua/[^\"]+)\"[^>]*>([^<]+)</a></li>")
        regex.findAll(doc.html()).take(10).forEach { m ->
            val href = m.groupValues[2]
            val title = m.groupValues[3]
            list.add(
                Manga(
                    id = generateUid(href),
                    url = href.removePrefix(baseUrl()),
                    publicUrl = href,
                    coverUrl = "${baseUrl()}/images/default/cover.png",
                    title = title,
                    altTitles = emptySet(),
                    rating = org.skepsun.kototoro.parsers.model.RATING_UNKNOWN,
                    tags = setOf(MangaTag("热门推荐", "热门推荐", source)),
                    authors = emptySet(),
                    state = null,
                    source = source,
                    contentRating = ContentRating.SAFE,
                    description = "",
                )
            )
        }
        return list
    }

    private fun parseList(items: Iterable<org.jsoup.nodes.Element>): List<Manga> {
        val list = mutableListOf<Manga>()
        items.forEach { item ->
            val link = item.selectFirst("a[href^=/manhua/]") ?: item.selectFirst("a[href^=/book/]") ?: return@forEach
            val href = link.attr("href")
            val title = link.attr("title").ifEmpty { link.text().trim() }
            val cover = item.selectFirst("img")?.attr("src")
            if (href.isNotEmpty() && title.isNotEmpty()) {
                val absolute = if (href.startsWith("http")) href else baseUrl() + href
                list.add(
                    Manga(
                        id = generateUid(href),
                        url = href,
                        publicUrl = absolute,
                        coverUrl = if (cover?.startsWith("http") == true) cover else cover?.let { baseUrl() + it },
                        title = title,
                        altTitles = emptySet(),
                        rating = org.skepsun.kototoro.parsers.model.RATING_UNKNOWN,
                        tags = emptySet(),
                        authors = emptySet(),
                        state = null,
                        source = source,
                        contentRating = ContentRating.SAFE,
                    )
                )
            }
        }
        return list
    }

    override suspend fun getDetails(manga: Manga): Manga {
        val href = if (manga.url.startsWith("http")) manga.url else baseUrl() + manga.url
        val resp = webClient.httpGet(href, getRequestHeaders())
        if (!resp.isSuccessful) return manga
        val doc = resp.parseHtml()
        val title = doc.selectFirst("h1.ebook-title")?.text()?.trim().orEmpty().ifEmpty { manga.title }
        val cover = doc.selectFirst(".ebook-cover img")?.attr("src")?.let { if (it.startsWith("http")) it else baseUrl() + it } ?: manga.coverUrl
        val desc = doc.selectFirst(".ebook-detail")?.text()?.trim().orEmpty()
        val tags = doc.select(".ebook-tags a").mapNotNull { a ->
            val t = a.text().trim()
            if (t.isNotEmpty()) MangaTag(t, t, source) else null
        }.toSet()
        val chapters = doc.select(".chapter-content a").reversed().mapIndexedNotNull { index, a ->
            val chHref = a.attr("href")
            val name = a.text().trim().ifEmpty { "Ch ${index + 1}" }
            if (chHref.isEmpty()) null else MangaChapter(
                id = generateUid("$chHref-${manga.id}"),
                url = chHref,
                title = name,
                number = (index + 1).toFloat(),
                volume = 0,
                scanlator = null,
                uploadDate = 0,
                branch = null,
                source = source,
            )
        }
        return manga.copy(
            title = title,
            coverUrl = cover,
            description = desc.ifEmpty { manga.description },
            tags = if (tags.isNotEmpty()) tags else manga.tags,
            chapters = chapters,
            contentRating = manga.contentRating ?: ContentRating.SAFE,
        )
    }

    override suspend fun getPages(chapter: MangaChapter): List<MangaPage> {
        val href = if (chapter.url.startsWith("http")) chapter.url else baseUrl() + chapter.url
        val resp = webClient.httpGet(href, getRequestHeaders())
        if (!resp.isSuccessful) return emptyList()
        val doc = resp.parseHtml()
        val images = doc.select("div#images img").mapIndexedNotNull { index, img ->
            val src = img.attr("data-src").ifEmpty { img.attr("src") }
            if (src.isEmpty()) null else {
                val url = if (src.startsWith("http")) src else baseUrl() + src
                MangaPage(
                    id = generateUid("$url-$index"),
                    url = url,
                    preview = url,
                    source = source,
                )
            }
        }
        return images
    }

    override suspend fun getPageUrl(page: MangaPage): String = page.url
}
