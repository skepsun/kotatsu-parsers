package org.skepsun.kototoro.parsers.site.zh

import okhttp3.Cookie
import okhttp3.Response
import okhttp3.HttpUrl.Companion.toHttpUrlOrNull
import org.jsoup.Jsoup
import org.jsoup.nodes.Document
import org.jsoup.nodes.Element
import org.skepsun.kototoro.parsers.MangaLoaderContext
import org.skepsun.kototoro.parsers.MangaParserAuthProvider
import org.skepsun.kototoro.parsers.MangaSourceParser
import org.skepsun.kototoro.parsers.config.ConfigKey
import org.skepsun.kototoro.parsers.core.PagedMangaParser
import org.skepsun.kototoro.parsers.exception.AuthRequiredException
import org.skepsun.kototoro.parsers.model.*
import org.skepsun.kototoro.parsers.util.*
import java.nio.charset.StandardCharsets
import java.util.ArrayList
import java.util.EnumSet
import java.util.LinkedHashSet

@MangaSourceParser("WENKU8", "轻小说文库", "zh", type = ContentType.NOVEL)
internal class Wenku8(context: MangaLoaderContext) :
    PagedMangaParser(context, MangaParserSource.WENKU8, pageSize = 20), MangaParserAuthProvider {

    override val configKeyDomain = ConfigKey.Domain("www.wenku8.net")

    override val availableSortOrders: Set<SortOrder> = EnumSet.of(
        SortOrder.UPDATED,
        SortOrder.NEWEST,
        SortOrder.POPULARITY,
    )

    override val filterCapabilities: MangaListFilterCapabilities
        get() = MangaListFilterCapabilities(
            isSearchSupported = true,
            isSearchWithFiltersSupported = true,
            isMultipleTagsSupported = true,
        )

    override suspend fun getFilterOptions(): MangaListFilterOptions = MangaListFilterOptions(
        availableTags = buildFilterTags(),
    )

    override val authUrl: String = "https://$domain/login.php"

    override suspend fun isAuthorized(): Boolean = context.cookieJar
        .getCookies(domain)
        .any { it.isLoginCookie() }

    override suspend fun getUsername(): String {
        val doc = webClient.httpGet("https://$domain/index.php").parseHtmlGBK("https://$domain/index.php").ensureAuthorized()
        val welcome = doc.selectFirst(".m_top .fl")?.text().orEmpty()
        val username = welcome.substringAfter("欢迎您", "").substringBefore('[').trim()
        if (username.isEmpty()) {
            throw AuthRequiredException(source)
        }
        return username
    }

    override suspend fun getListPage(
        page: Int,
        order: SortOrder,
        filter: MangaListFilter,
    ): List<Manga> {
        val url = when {
            !filter.query.isNullOrBlank() -> buildSearchUrl(filter.query!!, page)
            else -> buildCatalogUrl(filter, page)
        }
        val doc = webClient.httpGet(url).parseHtmlGBK(url).ensureAuthorized()
        return parseCatalog(doc)
    }

    override suspend fun getDetails(manga: Manga): Manga {
        val url = manga.url.toAbsoluteUrl(domain)
        val doc = runCatching { webClient.httpGet(url).parseHtmlGBK(url) }.getOrElse {
            return manga
        }

        // Check authorization but don't throw immediately - try to extract what we can
        val needsAuth = doc.selectFirst("form[name=frmlogin]") != null
        val infoBlock = doc.selectFirst("#content")

        // If no content block and needs auth, bail out but keep existing data
        if (infoBlock == null && needsAuth) {
            return manga
        }
        
        // If no content block but not auth issue, return manga as-is
        if (infoBlock == null) {
            return manga
        }
        
        val title = infoBlock.selectFirst("span b")?.text()?.trim().orEmpty().ifEmpty { manga.title }
        val cover = doc.selectFirst("#content img[src*=/files/]")?.attr("src")?.toAbsoluteUrl(domain)
        val description = infoBlock.extractSectionText("内容简介：")
        val tags = infoBlock.extractTags("作品Tags：").mapToLinkedTags()
        val author = infoBlock.extractValue("小说作者：")
        val state = parseState(infoBlock.extractValue("文章状态："))
        val chapterIndexUrl = infoBlock.selectFirst("a[href*=novel/][href$=index.htm]")
            ?.attr("href")
            ?.toAbsoluteUrl(domain)
            ?: buildChapterIndexUrl(manga)
        
        // Try to fetch chapters, but don't fail if auth is needed
        val chapters = try {
            fetchChapters(chapterIndexUrl)
        } catch (e: AuthRequiredException) {
            if (needsAuth) return manga.copy(
                title = title,
                description = description?.trim().takeIf { it?.isNotEmpty() == true } ?: manga.description,
                coverUrl = cover ?: manga.coverUrl,
                authors = author?.let { setOf(it) } ?: manga.authors,
                tags = if (tags.isNotEmpty()) tags else manga.tags,
                state = state ?: manga.state,
                chapters = emptyList(),
                altTitles = manga.altTitles,
                contentRating = manga.contentRating,
            )
            emptyList()
        }

        return manga.copy(
            title = title,
            description = description?.trim().takeIf { it?.isNotEmpty() == true } ?: manga.description,
            coverUrl = cover ?: manga.coverUrl,
            authors = author?.let { setOf(it) } ?: manga.authors,
            tags = if (tags.isNotEmpty()) tags else manga.tags,
            state = state ?: manga.state,
            chapters = chapters,
            altTitles = manga.altTitles,
            contentRating = manga.contentRating,
        )
    }

    override suspend fun getPages(chapter: MangaChapter): List<MangaPage> {
        val url = chapter.url.toAbsoluteUrl(domain)
        val html = runCatching {
            val doc = webClient.httpGet(url).parseHtmlGBK(url)
            if (doc.selectFirst("form[name=frmlogin]") != null) {
                buildErrorHtml("需要先登录后才能阅读本章节")
            } else {
                buildChapterHtml(doc, chapter.title ?: "")
            }
        }.getOrElse { throwable ->
            buildErrorHtml("加载章节失败：${throwable.message ?: "未知错误"}")
        }
        val dataUrl = html.toDataUrl()
        return listOf(
            MangaPage(
                id = generateUid(url),
                url = dataUrl,
                preview = null,
                source = source,
            ),
        )
    }

    override suspend fun getPageUrl(page: MangaPage): String = page.url.toAbsoluteUrl(domain)

    private fun parseCatalog(doc: Document): List<Manga> {
        val blocks = doc.select("table.grid div[style*=width:373px]")
        val list = ArrayList<Manga>(blocks.size)
        for (block in blocks) {
            val titleLink = block.selectFirst("b a[href]") ?: continue
            val href = titleLink.attr("href").toRelativeUrl(domain)
            val title = titleLink.text().trim()
            if (title.isEmpty()) continue
            val info = block.select("p")
            var author: String? = null
            var category: String? = null
            var updated: String? = null
            var size: String? = null
            var status: String? = null
            var desc: String? = null
            var tagSet: Set<MangaTag> = emptySet()
            for (p in info) {
                val text = p.text().trim()
                when {
                    text.startsWith("作者:") -> {
                        val parts = text.split('/')
                        author = parts.getOrNull(0)?.substringAfter("作者:")?.trim()
                        category = parts.getOrNull(1)?.substringAfter("分类:")?.trim()
                    }
                    text.startsWith("更新:") -> {
                        val parts = text.split('/')
                        updated = parts.getOrNull(0)?.substringAfter("更新:")?.trim()
                        size = parts.getOrNull(1)?.substringAfter("字数:")?.trim()
                        status = parts.getOrNull(2)?.substringAfter("状态:")?.trim()
                    }
                    text.startsWith("Tags:") -> {
                        val tags = p.select("span").text().split(' ', '　')
                            .mapNotNull { it.trim().takeIf(String::isNotEmpty) }
                        tagSet = tags.mapToLinkedTags()
                    }
                    text.startsWith("简介:") -> desc = text.substringAfter("简介:").trim()
                }
            }
            val cover = block.selectFirst("img[src]")?.attr("src")?.toAbsoluteUrl(domain)
            val state = parseState(status)
            list += Manga(
                id = generateUid(href),
                url = href,
                publicUrl = href.toAbsoluteUrl(domain),
                title = title,
                altTitles = emptySet(),
                coverUrl = cover,
                largeCoverUrl = null,
                authors = author?.let { setOf(it) } ?: emptySet(),
                tags = tagSet,
                description = desc,
                rating = RATING_UNKNOWN,
                contentRating = null,
                state = state,
                source = source,
            )
        }
        return list
    }

    private suspend fun fetchChapters(indexUrl: String): List<MangaChapter> {
        val doc = webClient.httpGet(indexUrl).parseHtmlGBK(indexUrl).ensureAuthorized()
        val chapters = ArrayList<MangaChapter>()
        var currentVolume: String? = null
        var volumeIndex = 0
        val baseUrl = indexUrl.toHttpUrlOrNull()
        for (cell in doc.select("td[class]")) {
            val clazz = cell.className().lowercase()
            if (clazz.contains("vcss")) {
                currentVolume = cell.text().trim()
                volumeIndex = parseVolumeNumber(currentVolume)
                continue
            }
            if (!clazz.contains("ccss")) continue
            val link = cell.selectFirst("a[href]") ?: continue
            
            // Get the href attribute
            val rawHref = link.attr("href")
            
            // Convert to absolute URL using the index page as base
            val absoluteUrl = when {
                baseUrl != null -> baseUrl.resolve(rawHref)?.toString().orEmpty()
                else -> rawHref.toAbsoluteUrl(domain)
            }
            
            // Convert back to relative URL for storage
            val href = absoluteUrl.toRelativeUrl(domain)
            
            val title = link.text().trim().ifEmpty { "Chapter ${chapters.size + 1}" }
            chapters += MangaChapter(
                id = generateUid(href),
                title = title,
                number = extractChapterNumber(title),
                volume = volumeIndex,
                url = href,
                scanlator = null,
                uploadDate = 0,
                branch = currentVolume,
                source = source,
            )
        }
        return chapters
    }

    private fun buildCatalogUrl(filter: MangaListFilter, page: Int): String {
        val url = StringBuilder()
            .append("https://").append(domain)
            .append("/modules/article/articlelist.php")
        val params = ArrayList<String>(3)
        filter.valueFor("initial")?.takeIf { it.isNotBlank() }?.let { params += "initial=$it" }
        filter.valueFor("class")?.takeIf { it.isNotBlank() }?.let { params += "class=$it" }
        if (page > 1) params += "page=$page"
        if (params.isNotEmpty()) {
            url.append('?').append(params.joinToString("&"))
        }
        return url.toString()
    }

    private fun buildSearchUrl(query: String, page: Int): String {
        return buildString {
            append("https://").append(domain)
                .append("/modules/article/search.php?searchtype=articlename&searchkey=")
                .append(query.urlEncoded())
            if (page > 1) {
                append("&page=").append(page)
            }
        }
    }

    private fun Document.ensureAuthorized(): Document {
        if (selectFirst("form[name=frmlogin]") != null) {
            throw AuthRequiredException(source)
        }
        return this
    }

    private fun Response.parseHtmlGBK(url: String): Document {
        val stream = body?.byteStream() ?: throw AuthRequiredException(source)
        return stream.use { Jsoup.parse(it, "GBK", url) }
    }

    private fun Element.extractValue(prefix: String): String? {
        return select("td").firstOrNull { it.text().contains(prefix) }
            ?.text()
            ?.substringAfter(prefix)
            ?.trim()
            ?.takeIf { it.isNotEmpty() }
    }

    private fun Element.extractTags(prefix: String): List<String> {
        val node = selectFirst("*:containsOwn($prefix)") ?: return emptyList()
        val text = node.text().substringAfter(prefix, "")
        if (text.isEmpty()) return emptyList()
        return text.split(' ', '　').mapNotNull { it.trim().takeIf(String::isNotEmpty) }
    }

    private fun Element.extractSectionText(prefix: String): String? {
        val node = selectFirst("*:containsOwn($prefix)") ?: return null
        val parent = node.parent()
        val builder = StringBuilder()
        var reachedTags = false
        parent?.children()?.forEach { child ->
            if (child == node) return@forEach
            val text = child.text()
            if (text.contains("作品Tags") || text.contains("作品热度")) {
                reachedTags = true
            }
            if (!reachedTags) {
                builder.append(child.outerHtml())
            }
        }
        return builder.toString()
            .replace("<br>", "\n")
            .replace("<br/>", "\n")
            .replace(Regex("<[^>]+>"), "")
            .replace("&nbsp;", " ")
            .trim()
            .takeIf { it.isNotEmpty() }
    }

    private fun List<String>.mapToLinkedTags(): Set<MangaTag> = mapTo(linkedSetOf()) { name ->
        MangaTag(name, name, source)
    }

    private fun parseVolumeNumber(text: String?): Int {
        if (text.isNullOrBlank()) return 0
        val digits = Regex("""\d+""").find(text)?.value ?: return 0
        return digits.toIntOrNull() ?: 0
    }

    private fun extractChapterNumber(title: String): Float {
        return Regex("""\d+(\.\d+)?""").find(title)?.value?.toFloatOrNull() ?: 0f
    }

    private fun buildChapterIndexUrl(manga: Manga): String {
        val id = Regex("""\d+""").find(manga.url)?.value ?: return manga.url
        val num = id.toIntOrNull() ?: return manga.url
        val prefix = num / 1000
        return "https://$domain/novel/$prefix/$id/index.htm"
    }

    private fun MangaListFilter.valueFor(prefix: String): String? {
        return tags.firstOrNull { it.key.startsWith("$prefix:") }
            ?.key
            ?.substringAfter(':')
    }

    private fun buildFilterTags(): Set<MangaTag> {
        val tags = LinkedHashSet<MangaTag>()
        LETTERS.forEach { letter ->
            tags += MangaTag("首字母 $letter", "initial:$letter", source)
        }
        tags += MangaTag("首字母 全部", "initial:", source)
        CATEGORIES.forEach { (name, value) ->
            tags += MangaTag(name, "class:$value", source)
        }
        return tags
    }

	private fun buildChapterHtml(doc: Document, title: String): String {
		val content = doc.selectFirst("#content") ?: return "<p>内容为空</p>"
		content.selectFirst("ul#contentdp")?.remove()
		content.select("script,style,iframe").remove()
		// 补全图片地址，避免相对路径导致丢图
		content.select("img[src]").forEach { img ->
			val abs = img.absUrl("src").ifBlank { img.attr("src") }
			if (abs.isNullOrBlank()) {
				img.remove()
			} else {
				img.attr("src", abs)
				img.attr("referrerpolicy", "no-referrer")
			}
		}
		val sanitized = content.html()
		return buildString {
			append("<!DOCTYPE html><html><head><meta charset=\"utf-8\"/>")
			append("<style>")
			append(
                "body{font-family:\"Noto Serif SC\",\"PingFang SC\",sans-serif;padding:16px;margin:0;" +
                    "line-height:1.9;font-size:1.05rem;}" +
                    "img{max-width:100%;height:auto;}p{margin:0 0 1rem;}h1{font-size:1.3rem;margin-bottom:1rem;}",
            )
            append("</style></head><body>")
            if (title.isNotBlank()) {
                append("<h1>").append(title).append("</h1>")
            }
            append(sanitized)
            append("</body></html>")
        }
    }

    private fun buildErrorHtml(message: String): String = """
        <!DOCTYPE html><html><head><meta charset="utf-8"/>
        <style>body{font-family:"Noto Serif SC","PingFang SC",sans-serif;padding:16px;line-height:1.8;background:#f8f5f1;color:#222;}
        </style></head><body><h1>提示</h1><p>$message</p></body></html>
    """.trimIndent()

    private fun String.toDataUrl(): String {
        val encoded = context.encodeBase64(toByteArray(StandardCharsets.UTF_8))
        return "data:text/html;charset=utf-8;base64,$encoded"
    }

    private fun Cookie.isLoginCookie(): Boolean {
        val name = name.lowercase()
        return name.contains("jieqi") || name.contains("phpdisk")
    }

    private fun parseState(raw: String?): MangaState? = when {
        raw.isNullOrBlank() -> null
        raw.contains("完结") -> MangaState.FINISHED
        raw.contains("连载") -> MangaState.ONGOING
        else -> null
    }

    private companion object {
        private val LETTERS = ('A'..'Z').map { it.toString() } + listOf("0-9")
        private val CATEGORIES = listOf(
            "角川文库" to "1",
            "电击文库" to "2",
            "一迅社文库" to "3",
            "MF文库J" to "4",
            "Fami通文库" to "5",
            "GA文库" to "6",
            "HJ文库" to "7",
            "一迅社" to "8",
        )
    }
}
