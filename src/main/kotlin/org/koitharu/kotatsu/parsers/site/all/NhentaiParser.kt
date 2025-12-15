@file:OptIn(org.skepsun.kototoro.parsers.InternalParsersApi::class)

package org.skepsun.kototoro.parsers.site.all

import okhttp3.Headers
import okhttp3.Interceptor
import okhttp3.Response
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
import org.skepsun.kototoro.parsers.util.parseJson
import org.skepsun.kototoro.parsers.util.urlEncoded
import java.util.EnumSet

/**
 * nhentai (nhentai.net)
 */
@MangaSourceParser("NHENTAI", "nhentai", "en")
internal class NhentaiParser(context: MangaLoaderContext) :
    PagedMangaParser(context, MangaParserSource.NHENTAI, pageSize = 25), Interceptor {

    override val configKeyDomain = org.skepsun.kototoro.parsers.config.ConfigKey.Domain("nhentai.net")
    override val availableSortOrders: Set<SortOrder> = EnumSet.of(SortOrder.NEWEST)

    override val filterCapabilities: MangaListFilterCapabilities
        get() = MangaListFilterCapabilities(isSearchSupported = true)

    override suspend fun getFilterOptions(): MangaListFilterOptions =
        MangaListFilterOptions(availableTags = emptySet(), availableContentRating = EnumSet.of(ContentRating.ADULT))

    override fun getRequestHeaders(): Headers = Headers.Builder()
        .add("User-Agent", UserAgents.CHROME_DESKTOP)
        .build()

    override fun intercept(chain: Interceptor.Chain): Response {
        // 需要 Referer 才能加载图片
        val req = chain.request()
        val url = req.url.toString()
        return if (url.contains("nhentai.net")) {
            val newReq = req.newBuilder()
                .header("Referer", "https://$domain/")
                .header("User-Agent", UserAgents.CHROME_DESKTOP)
                .build()
            chain.proceed(newReq)
        } else {
            chain.proceed(req)
        }
    }

    override suspend fun getListPage(page: Int, order: SortOrder, filter: MangaListFilter): List<Manga> {
        val query = filter.query.orEmpty()
        val url = if (query.isNotEmpty()) {
            "https://${domain}/search/?q=${query.urlEncoded()}&page=$page"
        } else {
            "https://${domain}/?page=$page"
        }
        val resp = webClient.httpGet(url, getRequestHeaders())
        if (!resp.isSuccessful) return emptyList()
        val doc = resp.parseHtml()
        return parseGalleryList(doc)
    }

    private fun parseGalleryList(doc: Document): List<Manga> {
        return doc.select("div.gallery").mapNotNull { el ->
            val a = el.selectFirst("a") ?: return@mapNotNull null
            val href = a.attr("href")
            val id = href.replace(Regex("\\D"), "")
            val title = el.selectFirst(".caption")?.text()?.trim().orEmpty()
            val cover = el.selectFirst("a > img")?.attr("data-src")
                ?: el.selectFirst("a > img")?.attr("src")
            val lang = when {
                el.attr("data-tags").contains("12227") -> "English"
                el.attr("data-tags").contains("6346") -> "日本語"
                el.attr("data-tags").contains("29963") -> "中文"
                else -> ""
            }
            if (id.isEmpty() || title.isEmpty()) return@mapNotNull null
            Manga(
                id = generateUid(id),
                title = title,
                altTitles = emptySet(),
                url = id,
                publicUrl = "https://${domain}/g/$id",
                rating = org.skepsun.kototoro.parsers.model.RATING_UNKNOWN,
                contentRating = ContentRating.ADULT,
                coverUrl = cover,
                tags = emptySet(),
                state = null,
                authors = emptySet(),
                source = source,
                description = lang,
            )
        }
    }

    override suspend fun getDetails(manga: Manga): Manga {
        val resp = webClient.httpGet("https://${domain}/g/${manga.url}", getRequestHeaders())
        if (!resp.isSuccessful) return manga
        val doc = resp.parseHtml()
        val title = doc.selectFirst("#info h1")?.text()?.trim().orEmpty().ifEmpty { manga.title }
        val tags = doc.select("#info .tag-container a").mapNotNull {
            val t = it.selectFirst(".name")?.text()?.trim().orEmpty()
            if (t.isNotEmpty()) MangaTag(t, t, source) else null
        }.toSet()
        val pages = doc.select(".gallerythumb a img").mapIndexedNotNull { index, img ->
            val thumb = img.attr("data-src").ifEmpty { img.attr("src") }
            val full = thumb.replace("t.nhentai.net", "i.nhentai.net")
                .replace("/t.", "/")
            MangaPage(
                id = generateUid(full),
                url = full,
                preview = full,
                source = source,
            )
        }
        val lang = doc.select("#info .tag-container .name").map { it.text() }
            .firstOrNull { it in setOf("english", "japanese", "chinese") }
        return manga.copy(
            title = title,
            tags = if (tags.isNotEmpty()) tags else manga.tags,
            chapters = listOf(
                MangaChapter(
                    id = generateUid("${manga.id}-0"),
                    title = "Chapter 1",
                    number = 1f,
                    volume = 0,
                    url = manga.url,
                    scanlator = null,
                    uploadDate = 0,
                    branch = null,
                    source = source,
                )
            ),
            contentRating = ContentRating.ADULT,
            description = lang ?: manga.description,
        )
    }

    override suspend fun getPages(chapter: MangaChapter): List<MangaPage> {
        // 已在详情解析 pages，可直接返回；若未解析则 fallback 重新抓
        val mangaId = chapter.url
        val resp = webClient.httpGet("https://${domain}/g/$mangaId", getRequestHeaders())
        if (!resp.isSuccessful) return emptyList()
        val doc = resp.parseHtml()
        return doc.select(".gallerythumb a img").mapIndexedNotNull { index, img ->
            val thumb = img.attr("data-src").ifEmpty { img.attr("src") }
            val full = thumb.replace("t.nhentai.net", "i.nhentai.net")
                .replace("/t.", "/")
            MangaPage(id = generateUid(full), url = full, preview = full, source = source)
        }
    }

    override suspend fun getPageUrl(page: MangaPage): String = page.url
}
