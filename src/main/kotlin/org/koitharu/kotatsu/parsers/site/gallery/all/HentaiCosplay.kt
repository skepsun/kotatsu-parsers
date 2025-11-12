package org.skepsun.kototoro.parsers.site.gallery.all

import okhttp3.Headers
import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response
import kotlinx.coroutines.*
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock
import org.jsoup.nodes.Element
import org.skepsun.kototoro.parsers.*
import org.skepsun.kototoro.parsers.model.*
import org.skepsun.kototoro.parsers.site.gallery.GalleryParser
import org.skepsun.kototoro.parsers.util.*

@MangaSourceParser("HENTAI_COSPLAY", "Hentai Cosplay", type = ContentType.OTHER)
internal class HentaiCosplay(context: MangaLoaderContext) :
    GalleryParser(context, MangaParserSource.HENTAI_COSPLAY, "hentai-cosplay-xxx.com") {

    companion object {
        private val mutex = Mutex()
        private var lastImageRequestTime = 0L
        private val IMAGE_EXTENSIONS = listOf(".jpg", ".jpeg", ".png", ".webp")
    }

    override suspend fun getList(offset: Int, order: SortOrder, filter: MangaListFilter): List<Manga> {
        val url = urlBuilder().apply {
            if (!filter.query.isNullOrEmpty()) {
                addPathSegment("default")
                addPathSegment("images")
                addPathSegment("process-search")
                addQueryParameter("keyword", filter.query)
            } else {
                // 首页就是图集列表，不需要添加额外路径
                if (offset > 0) {
                    addQueryParameter("start", offset.toString())
                }
            }
        }.build()

        val content = webClient.httpGet(url).parseHtml()
        
        return content.select("div.image-list-item").map { item ->
            // 查找包含标题的链接
            val titleLink = item.select("a").firstOrNull { it.text().isNotBlank() }
            val relUrl = titleLink?.attr("href") ?: ""
            val title = titleLink?.text() ?: ""
            
            // 查找封面图片
            val coverEl = item.selectFirst("div.image-list-item-image img")
            val coverUrl = coverEl?.attr("src")
            
            Manga(
                id = generateUid(relUrl),
                url = relUrl,
                title = title,
                altTitles = emptySet(),
                publicUrl = relUrl.toAbsoluteUrl(domain),
                rating = RATING_UNKNOWN,
                contentRating = ContentRating.ADULT,
                coverUrl = coverUrl,
                tags = emptySet(),
                state = MangaState.FINISHED,
                authors = emptySet(),
                largeCoverUrl = null,
                description = null,
                chapters = null,
                source = source,
            )
        }
    }

    override suspend fun getDetails(manga: Manga): Manga {
        val content = webClient.httpGet(manga.url.toAbsoluteUrl(domain)).parseHtml()
        
        val description = content.selectFirst("div.image-description")?.text()?.trim() ?: ""
        
        // 创建单章节（整个图集作为一个章节）
        val chapter = MangaChapter(
            id = generateUid(manga.url),
            title = "Full Gallery",
            number = 1f,
            volume = 0,
            url = manga.url,
            scanlator = null,
            uploadDate = System.currentTimeMillis(),
            branch = null,
            source = source,
        )
        
        return manga.copy(
            description = description,
            chapters = listOf(chapter)
        )
    }

    override suspend fun getPages(chapter: MangaChapter): List<MangaPage> {
        val content = webClient.httpGet(chapter.url.toAbsoluteUrl(domain)).parseHtml()
        
        // 查找所有图片链接 - 使用 data-modal-gallery-image-item 属性
        return content.select("a[data-modal-gallery-image-item]").mapNotNull { linkEl ->
            val url = linkEl.attrOrNull("href") ?: return@mapNotNull null
            MangaPage(
                id = generateUid(url),
                url = url,
                preview = null,
                source = source,
            )
        }
    }

    override fun getRequestHeaders(): Headers = Headers.Builder()
        .add("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/120.0.0.0 Safari/537.36")
        .add("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,*/*;q=0.8")
        .add("Accept-Language", "en-US,en;q=0.5")
        .add("Referer", "https://$domain/")
        .build()

    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
        val url = request.url.toString()

        // 图片请求限流
        if (IMAGE_EXTENSIONS.any { url.endsWith(it, ignoreCase = true) }) {
            runBlocking {
                mutex.withLock {
                    val now = System.currentTimeMillis()
                    val wait = 500L - (now - lastImageRequestTime)
                    if (wait > 0) {
                        delay(wait)
                    }
                    lastImageRequestTime = System.currentTimeMillis()
                }
            }
        }

        return chain.proceed(request)
    }
}