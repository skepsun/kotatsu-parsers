package org.koitharu.kotatsu.parsers.site.zh

import okhttp3.Headers
import okhttp3.Interceptor
import okhttp3.Response
import org.json.JSONArray
import org.json.JSONObject
import org.koitharu.kotatsu.parsers.MangaLoaderContext
import org.koitharu.kotatsu.parsers.MangaSourceParser
import org.koitharu.kotatsu.parsers.config.ConfigKey
import org.koitharu.kotatsu.parsers.core.PagedMangaParser
import org.koitharu.kotatsu.parsers.model.*
import org.koitharu.kotatsu.parsers.network.UserAgents
import org.koitharu.kotatsu.parsers.util.generateUid
import org.koitharu.kotatsu.parsers.util.json.mapJSON
import org.koitharu.kotatsu.parsers.util.json.mapJSONIndexed
import org.koitharu.kotatsu.parsers.util.parseHtml
import org.koitharu.kotatsu.parsers.util.parseJson
import org.koitharu.kotatsu.parsers.util.urlEncoded
import kotlin.random.Random
import java.util.EnumSet

/**
 * 拷贝漫画（新站）解析器
 * 参考 /Users/sunchuxiong/kotatsu_demo/copymanga.js
 */
@MangaSourceParser("COPYMANGA", "CopyManga", "zh")
internal class CopyMangaParser(context: MangaLoaderContext) :
    PagedMangaParser(context, MangaParserSource.COPYMANGA, pageSize = 21), Interceptor {
    init {
        // 统一从第 1 页开始，以匹配 /comics?page=1 的分页策略
        paginator.firstPage = 1
        searchPaginator.firstPage = 1
    }

    // 默认 API 域，后续通过 refreshAppApi 动态更新
    override val configKeyDomain = ConfigKey.Domain(
        "www.mangacopy.com",
        "www.2025copy.com",
        "api.2025copy.com",
        "www.copy20.com",
        "api.copy2000.online",
    )
    override val userAgentKey = ConfigKey.UserAgent(UserAgents.CHROME_DESKTOP)
    // 运行期设备参数（一次生成，实例持有）
    private val deviceInfo: String by lazy { generateDeviceInfo() }
    private val device: String by lazy { generateDevice() }
    private val pseudoId: String by lazy { generatePseudoId() }
    private var baseUrlOverride: String? = null
    // 站点域（用于图片 Referer/Origin、HTML 回退等）
    private var siteDomainOverride: String? = null
    private fun siteDomain(): String {
        val raw = siteDomainOverride ?: config[configKeyDomain]
        return if (raw.startsWith("api.")) raw.replaceFirst("api.", "www.") else raw
    }
    private val imageQuality: String = "1500"
    // 主题分类映射（简化为空，接口将返回全部）
    private val CATEGORY_PARAM_DICT: Map<String, String> = mapOf(
        // 最小可用映射，用户反馈可正常浏览
        "爱情" to "aiqing",
    )

    override fun onCreateConfig(keys: MutableCollection<ConfigKey<*>>) {
        super.onCreateConfig(keys)
        keys.add(userAgentKey)
    }

    override val availableSortOrders: Set<SortOrder> = EnumSet.of(
        SortOrder.UPDATED,
        SortOrder.POPULARITY_MONTH,
        SortOrder.POPULARITY,
    )

    override val filterCapabilities: MangaListFilterCapabilities
        get() = MangaListFilterCapabilities(
            isMultipleTagsSupported = false,
            isTagsExclusionSupported = false,
            isSearchSupported = true,
            isSearchWithFiltersSupported = false,
        )

    override suspend fun getFilterOptions(): MangaListFilterOptions {
        // 将 JS 中的“主题”和“排行”的选择映射为标签组（固定分类）
        val themeTags: Set<MangaTag> = CATEGORY_PARAM_DICT.entries.map { entry ->
            MangaTag(title = entry.key, key = entry.value, source = source)
        }.toSet()
        val rankingTag = MangaTag(title = "排行", key = "ranking", source = source)
        return MangaListFilterOptions(
            availableTags = themeTags + setOf(rankingTag),
            availableStates = EnumSet.of(MangaState.ONGOING, MangaState.FINISHED),
            availableContentRating = emptySet(),
        )
    }

    override fun getRequestHeaders(): Headers = Headers.Builder()
        .add("User-Agent", config[userAgentKey])
        .add("source", "copyApp")
        .add("deviceinfo", deviceInfo)
        .add("platform", "3")
        .add("referer", "com.copymanga.app-3.0.0")
        .add("version", "3.0.0")
        .add("device", device)
        .add("pseudoid", pseudoId)
        .add("Accept", "application/json")
        .add("region", DEFAULT_REGION)
        .apply {
            // 附加 Token
            add("authorization", "Token")
            // 认证时间戳与签名
            val now = java.util.Date()
            val cal = java.util.Calendar.getInstance().apply { time = now }
            val year = cal.get(java.util.Calendar.YEAR)
            val month = (cal.get(java.util.Calendar.MONTH) + 1).toString().padStart(2, '0')
            val day = cal.get(java.util.Calendar.DAY_OF_MONTH).toString().padStart(2, '0')
            val ts = (now.time / 1000).toString()
            add("dt", "$year.$month.$day")
            add("x-auth-timestamp", ts)
            val sig = hmacSha256(COPY_SECRET, ts)
            add("x-auth-signature", sig)
            add("umstring", "b4c89ca4104ea9a97750314d791520ac")
        }
        .build()

    private fun apiBase(): String {
        return baseUrlOverride ?: config[configKeyDomain]
    }

    // 动态刷新 API 端点（优先通过官方 network2 接口，其次回退 HTML 提取 countApi）
    private suspend fun refreshAppApi(): String {
        // 1) 优先使用官方接口：api.copy-manga.com/api/v3/system/network2?platform=3
        runCatching {
            val url = "https://api.copy-manga.com/api/v3/system/network2?platform=3"
            val data = webClient.httpGet(url, getRequestHeaders()).parseJson()
            val api = data.optJSONObject("results")
                ?.optJSONArray("api")
                ?.optJSONArray(0)
                ?.optString(0)
            val base = api?.takeIf { it.isNotBlank() }
            // 解析 share 站点域，用于图片请求的 Referer/Origin
            val share = data.optJSONObject("results")
                ?.optJSONArray("share")
                ?.optString(0)
            if (!share.isNullOrBlank()) {
                siteDomainOverride = share.removePrefix("https://")
            }
            if (!base.isNullOrEmpty()) {
                baseUrlOverride = base
                return base
            }
        }.onFailure { /* ignore and try next */ }

        // 2) 失败时回退至 HTML 页面提取 countApi（部分站点可能仍可用）
        val candidates = listOf("www.mangacopy.com", "www.2025copy.com", "www.copy20.com")
        val paths = listOf("/search", "/")
        for (host in candidates) {
            for (path in paths) {
                val url = "https://$host$path"
                runCatching {
                    val text = webClient.httpGet(url, getRequestHeaders()).parseHtml().outerHtml()
                    val m = REGEX_COUNT_API.find(text)
                    val endpoint = m?.groups?.get(1)?.value
                    val base = endpoint?.substringBefore("/api/")?.removePrefix("https://")
                    if (!base.isNullOrEmpty()) {
                        baseUrlOverride = base
                        return base
                    }
                }.onFailure { /* ignore and try next */ }
            }
        }
        return apiBase()
    }

    override suspend fun getListPage(page: Int, order: SortOrder, filter: MangaListFilter): List<Manga> {
        val base = refreshAppApi()
        val offset = (page - paginator.firstPage) * pageSize
        val isRanking = filter.tags.any { it.key == "ranking" }
        val url = if (isRanking) {
            // 排行接口：audience_type 与 date_type 作为“选项”绑定到 tags 中的 key 或查询
            val audience = filter.query?.substringBefore('|') ?: "1" // 默认 type=1
            val dateType = filter.query?.substringAfter('|') ?: "month" // 默认最近30天
            buildString {
                append("https://")
                append(base)
                append("/api/v3/ranks?limit=")
                append(pageSize)
                append("&offset=")
                append(offset)
                append("&_update=true&type=1&audience_type=")
                append(audience)
                append("&date_type=")
                append(dateType)
                append("&free_type=1")
            }
        } else if (!filter.query.isNullOrEmpty()) {
            // 搜索接口：优先 webAPI（copymanga.js 中 refreshSearchApi 可能改变路径），这里简化为统一 API
            val q = filter.query.urlEncoded()
            buildString {
                append("https://")
                append(base)
                append(COPY_SEARCH_API)
                append("?limit=")
                append(pageSize)
                append("&offset=")
                append(offset)
                append("&q=")
                append(q)
                append("&q_type=")
                append("name")
                append("&_update=true&free_type=1")
            }
        } else {
            // 主题分类列表
            val themeParam = filter.tags.firstOrNull()?.key ?: ""
            val ordering = "-datetime_updated" // 默认时间倒序
            val top = when {
                MangaState.FINISHED in filter.states -> "finish"
                MangaState.ONGOING in filter.states -> "-全部"
                else -> "-全部"
            }
            buildString {
                append("https://")
                append(base)
                append("/api/v3/comics?limit=")
                append(pageSize)
                append("&offset=")
                append(offset)
                append("&ordering=")
                append(ordering)
                append("&theme=")
                append(themeParam)
                append("&top=")
                append(top)
                append("&_update=true&free_type=1")
            }
        }

        // 先尝试 API；若返回维护页/异常/空列表，则改用 HTML 列表页解析
        val apiResults = runCatching {
            val resp = webClient.httpGet(url, getRequestHeaders())
            val root = resp.parseJson()
            val list = root.optJSONObject("results")?.optJSONArray("list") ?: JSONArray()
            list.mapJSON { jo ->
                val comic = jo.optJSONObject("comic") ?: jo
                val id = comic.optString("path_word")
                val title = comic.optString("name")
                val cover = comic.optString("cover")
                val tagsArray = comic.optJSONArray("theme") ?: JSONArray()
                val tags = tagsArray.mapJSON { t ->
                    val n = t.optString("name")
                    MangaTag(title = n, key = n, source = source)
                }.toSet()
                val authors = (comic.optJSONArray("author") ?: JSONArray()).mapJSON { a -> a.optString("name") }.toSet()
                Manga(
                    id = generateUid(id),
                    url = id,
                    publicUrl = "https://${config[configKeyDomain]}/comic/$id",
                    coverUrl = cover,
                    title = title,
                    altTitles = emptySet(),
                    rating = RATING_UNKNOWN,
                    tags = tags,
                    authors = authors,
                    state = null,
                    source = source,
                    contentRating = null,
                )
            }
        }.getOrElse { emptyList() }

        if (apiResults.isNotEmpty()) {
            return apiResults
        }

        // HTML 列表页回退：注意不要使用 API 域，需使用站点域（www.*）
        val themeParam = filter.tags.firstOrNull()?.key.orEmpty()
        val orderingParam = "-datetime_updated"
        val siteDomain = siteDomain()
        val htmlUrl = buildString {
            append("https://")
            append(siteDomain)
            append("/comics?")
            if (themeParam.isNotEmpty()) {
                append("theme=")
                append(themeParam)
                append("&")
            }
            append("ordering=")
            append(orderingParam)
            append("&page=")
            append(page)
        }

        val doc = webClient.httpGet(htmlUrl, getRequestHeaders()).parseHtml()
        val anchors = doc.select("a[href^=/comic/]")
        val seen = HashSet<String>()
        return anchors.mapNotNull { a ->
            val href = a.attr("href")
            val slug = href.removePrefix("/comic/").substringBefore('/')
            if (slug.isEmpty() || !seen.add(slug)) {
                null
            } else {
                val img = a.selectFirst("img") ?: a.parent()?.selectFirst("img")
                val cover = img?.attr("data-src").orEmpty().ifEmpty { img?.attr("src").orEmpty() }
                val container = a.parent() ?: a
                val titleCandidates = listOf(
                    a.attr("title"),
                    img?.attr("alt") ?: "",
                    container.selectFirst(".title, .name, .comics-title, .comics-name, .card-title, h3, h4, p, span")?.text()
                        ?: "",
                    a.text(),
                )
                val title = titleCandidates.firstOrNull { it.isNotBlank() && HAN_REGEX.containsMatchIn(it) }
                    ?.trim()
                    ?: titleCandidates.filter { it.isNotBlank() }.maxByOrNull { it.length }?.trim()
                    ?: slug
                Manga(
                    id = generateUid(slug),
                    url = slug,
                    publicUrl = "https://${config[configKeyDomain]}/comic/$slug",
                    coverUrl = if (cover.startsWith("http")) cover else "https://${config[configKeyDomain]}$cover",
                    title = title,
                    altTitles = emptySet(),
                    rating = RATING_UNKNOWN,
                    tags = emptySet(),
                    authors = emptySet(),
                    state = null,
                    source = source,
                    contentRating = null,
                )
            }
        }
    }

    override suspend fun getDetails(manga: Manga): Manga {
        // 优先使用 HTML 详情页，避免 API 维护导致 404
        val domain = siteDomain()
        val htmlUrl = "https://$domain/comic/${manga.url}"
        var htmlTitle: String? = null
        var htmlCover: String? = null
        var htmlDesc: String? = null
        var htmlState: MangaState? = null
        val doc = runCatching { webClient.httpGet(htmlUrl, getRequestHeaders()).parseHtml() }.getOrNull()
        if (doc != null) {
            val metaTitle = doc.selectFirst("meta[property=og:title]")?.attr("content").orEmpty()
            val h1Title = doc.selectFirst("h1, .comic-title, .comics-detail-title, .top-title")?.text().orEmpty()
            htmlTitle = listOf(metaTitle, h1Title).firstOrNull { it.isNotBlank() } ?: manga.title

            val metaImg = doc.selectFirst("meta[property=og:image]")?.attr("content").orEmpty()
            val coverImg = doc.selectFirst("img.cover, .poster img, .comic-cover img")?.attr("src").orEmpty()
            htmlCover = listOf(metaImg, coverImg).firstOrNull { it.isNotBlank() }
                ?.let { if (it.startsWith("http")) it else "https://$domain$it" }
                ?: manga.coverUrl

            val metaDesc = doc.selectFirst("meta[property=og:description]")?.attr("content").orEmpty()
            val brief = doc.selectFirst(".brief, .intro, .desc, .description")?.text().orEmpty()
            htmlDesc = listOf(metaDesc, brief).firstOrNull { it.isNotBlank() } ?: manga.description

            val stateText = doc.selectFirst(".status, .state, .comic-status")?.text().orEmpty()
            htmlState = when {
                stateText.contains("完结") -> MangaState.FINISHED
                stateText.contains("连载") -> MangaState.ONGOING
                else -> manga.state
            }

            val chapterAnchors = doc.select("a[href^=/comic/${manga.url}/chapter/], a[href^=/h5/comicContent/${manga.url}/]")
            val seen = HashSet<String>()
            var indexCounter = 0
            val chaptersFromHtml = chapterAnchors.mapNotNull { a ->
                val href = a.attr("href")
                val id = href.substringAfter("/chapter/").substringBefore('?').substringBefore('/')
                if (id.isBlank() || !seen.add(id)) {
                    null
                } else {
                    indexCounter += 1
                    val text = a.attr("title").ifEmpty { a.text() }
                    val number = a.attr("data-idx").toFloatOrNull()
                        ?: Regex("(\\d+(?:\\.\\d+)?)").find(text)?.groupValues?.get(1)?.toFloatOrNull()
                        ?: indexCounter.toFloat()
                    MangaChapter(
                        id = generateUid(id),
                        title = if (text.isNotBlank()) text else id,
                        number = number,
                        volume = 0,
                        url = id,
                        scanlator = null,
                        uploadDate = 0L,
                        branch = manga.url,
                        source = source,
                    )
                }
            }

            // 如果 HTML 章节为空，尝试解析 Next.js 的 __NEXT_DATA__
            val chaptersFromNext = runCatching {
                val nextRaw = doc.selectFirst("script#__NEXT_DATA__")?.html()
                if (!nextRaw.isNullOrBlank()) {
                    val next = org.json.JSONObject(nextRaw)
                    val props = next.optJSONObject("props")
                    val pageProps = props?.optJSONObject("pageProps") ?: org.json.JSONObject()
                    val groups = pageProps.optJSONArray("groups")
                        ?: pageProps.optJSONObject("detail")?.optJSONArray("groups")
                        ?: pageProps.optJSONObject("comic_info")?.optJSONArray("groups")
                        ?: org.json.JSONArray()
                    val listAll = java.util.ArrayList<MangaChapter>()
                    for (i in 0 until groups.length()) {
                        val g = groups.optJSONObject(i) ?: continue
                        val list = g.optJSONArray("list") ?: g.optJSONArray("chapters") ?: org.json.JSONArray()
                        for (j in 0 until list.length()) {
                            val item = list.optJSONObject(j) ?: continue
                            val id = item.optString("path_word", item.optString("id"))
                            if (id.isNullOrBlank()) continue
                            val name = item.optString("name", id)
                            val idxStr = item.optString("index")
                            val number = idxStr.toFloatOrNull() ?: (j + 1).toFloat()
                            listAll += MangaChapter(
                                id = generateUid(id),
                                title = name,
                                number = number,
                                volume = 0,
                                url = id,
                                scanlator = null,
                                uploadDate = 0L,
                                branch = manga.url,
                                source = source,
                            )
                        }
                    }
                    listAll
                } else emptyList()
            }.getOrDefault(emptyList())

            // 仅当 HTML 真正提取到章节时才使用 HTML 结果；否则尝试 __NEXT_DATA__，再回退到 API
            if (chaptersFromHtml.isNotEmpty()) {
                return manga.copy(
                    title = htmlTitle ?: manga.title,
                    coverUrl = htmlCover ?: manga.coverUrl,
                    largeCoverUrl = htmlCover ?: manga.coverUrl,
                    description = htmlDesc ?: manga.description,
                    state = htmlState ?: manga.state,
                    chapters = chaptersFromHtml.sortedBy { it.number },
                )
            } else if (chaptersFromNext.isNotEmpty()) {
                return manga.copy(
                    title = htmlTitle ?: manga.title,
                    coverUrl = htmlCover ?: manga.coverUrl,
                    largeCoverUrl = htmlCover ?: manga.coverUrl,
                    description = htmlDesc ?: manga.description,
                    state = htmlState ?: manga.state,
                    chapters = chaptersFromNext.sortedBy { it.number },
                )
            }
        }

        // HTML 不可用时优先使用抓包的 H5 接口（使用动态 API 域）
        val base = refreshAppApi()
        val url = "https://$base/api/v3/comic2/${manga.url}?platform=1&_update=true"
        val data = webClient.httpGet(url, getRequestHeaders()).parseJson()
        val res = data.optJSONObject("results") ?: return manga
        val comic = res.optJSONObject("comic") ?: return manga
        val title = (doc?.let { htmlTitle } ?: comic.optString("name", manga.title)) ?: manga.title
        val cover = (doc?.let { htmlCover } ?: comic.optString("cover", manga.coverUrl)) ?: manga.coverUrl
        val desc = (doc?.let { htmlDesc } ?: comic.optString("brief", manga.description)) ?: manga.description
        val stateStr = comic.optString("status", "")
        val apiState = when (stateStr.lowercase()) {
            "finished", "end" -> MangaState.FINISHED
            "ongoing" -> MangaState.ONGOING
            else -> manga.state
        }
        val state = doc?.let { htmlState } ?: apiState
        // 章节列表
        val groups = res.optJSONArray("groups") ?: JSONArray()
        val chapters = ArrayList<MangaChapter>()
        // 如果 API 未返回分组，默认抓取 default 组
        val pathList = mutableListOf<String>()
        if (groups.length() > 0) {
            for (i in 0 until groups.length()) {
                val g = groups.optJSONObject(i) ?: continue
                val path = g.optString("path_word", g.optString("path"))
                if (path.isNotBlank()) pathList += path
            }
        } else {
            pathList += "default"
        }
        for (path in pathList) {
            val chUrl = "https://$base/api/v3/comic/${manga.url}/group/$path/chapters?limit=100&offset=0&_update=true"
            val groupData = webClient.httpGet(chUrl, getRequestHeaders()).parseJson()
            val list = groupData.optJSONObject("results")?.optJSONArray("list") ?: JSONArray()
            for (j in 0 until list.length()) {
                val c = list.optJSONObject(j) ?: continue
                val serial = c.optString("name", "${j + 1}")
                val id = c.optString("path_word", "${manga.url}-$j")
                val number = (c.optString("index", "${j + 1}").toFloatOrNull() ?: (j + 1).toFloat())
                chapters += MangaChapter(
                    id = generateUid(id),
                    title = serial,
                    number = number,
                    volume = 0,
                    url = id,
                    scanlator = null,
                    uploadDate = 0L,
                    branch = manga.url,
                    source = source,
                )
            }
        }
        return manga.copy(
            title = title,
            coverUrl = cover,
            largeCoverUrl = cover,
            description = desc,
            state = state,
            chapters = chapters.sortedBy { it.number },
        )
    }

    override suspend fun getPages(chapter: MangaChapter): List<MangaPage> {
        val base = refreshAppApi()
        val url = "https://$base/api/v3/comic/${chapter.branch}/chapter2/${chapter.url}?platform=1&_update=true&line=1"
        var attempt = 0
        val maxAttempts = 5
        while (attempt < maxAttempts) {
            val response = webClient.httpGet(url, getRequestHeaders())
            if (response.code == 210) {
                // 访问过于频繁，解析等待时间并重试
                val defaultWaitMs = 40_000L
                val waitMs = runCatching {
                    val body = response.parseJson()
                    val msg = body.optString("message")
                    val m = Regex("(\\d+)\\s*seconds").find(msg)
                    val seconds = m?.groups?.get(1)?.value?.toLongOrNull()
                        ?: msg.toLongOrNull() // 兼容仅返回数字的情况（如 "93"）
                        ?: 40L
                    seconds * 1000L
                }.getOrElse { defaultWaitMs }
                kotlinx.coroutines.delay(waitMs)
                attempt++
                continue
            }

            val data = response.parseJson()
            val res = data.optJSONObject("results") ?: JSONObject()
            val chapterObj = res.optJSONObject("chapter") ?: JSONObject()
            val contents = chapterObj.optJSONArray("contents") ?: JSONArray()
            val orders = chapterObj.optJSONArray("words") ?: JSONArray()

            val urls = ArrayList<String>(contents.length())
            for (i in 0 until contents.length()) {
                val item = contents.optJSONObject(i) ?: continue
                val rawUrl = item.optString("url")
                if (rawUrl.isNullOrEmpty()) continue
                // 将原始 URL 重写为选定质量的 webp，保持与 venera 的 loadEp 一致
                val hdUrl = rawUrl.replace(Regex("([./])c\\d+x\\.[a-zA-Z]+$"), "$1c${imageQuality}x.webp")
                urls += hdUrl
            }

            // 根据 words 排序；若映射失败则采用原始顺序回退
            val pagesByOrder = MutableList(urls.size) { "" }
            for (i in urls.indices) {
                val pos = orders.optInt(i, i)
                if (pos in pagesByOrder.indices) {
                    pagesByOrder[pos] = urls[i]
                }
            }
            val ordered = pagesByOrder.mapIndexedNotNull { i, u ->
                if (u.isEmpty()) null else MangaPage(
                    id = generateUid("${chapter.url}/$i"),
                    url = u,
                    preview = null,
                    source = source,
                )
            }
            if (ordered.isNotEmpty()) {
                return ordered
            }
            if (urls.isNotEmpty()) {
                // 回退：使用原始顺序（仅当存在内容时）
                return urls.mapIndexed { i, u ->
                    MangaPage(
                        id = generateUid("${chapter.url}/$i"),
                        url = u,
                        preview = null,
                        source = source,
                    )
                }
            }
            // 无内容则继续尝试下一个循环
            attempt++
            continue
        }
        // 尝试 cartoon 端点作为回退（H5 端脚本中存在该路径）
        runCatching {
            val base = refreshAppApi()
            val altUrl = "https://$base/api/v3/cartoon/${chapter.branch}/chapter/${chapter.url}?platform=1&_update=true&line=1"
            val altResp = webClient.httpGet(altUrl, getRequestHeaders())
            if (altResp.code != 210) {
                val altData = altResp.parseJson()
                val altChapter = altData.optJSONObject("results")?.optJSONObject("chapter") ?: JSONObject()
                val altContents = altChapter.optJSONArray("contents") ?: JSONArray()
                val altUrls = ArrayList<String>(altContents.length())
                for (i in 0 until altContents.length()) {
                    val item = altContents.optJSONObject(i) ?: continue
                    val rawUrl = item.optString("url")
                    if (rawUrl.isNullOrEmpty()) continue
                    // 保留服务端返回的原始 URL，避免路径变化破坏签名
                    altUrls += rawUrl
                }
                if (altUrls.isNotEmpty()) {
                    return altUrls.mapIndexed { i, u ->
                        MangaPage(
                            id = generateUid("${chapter.url}/$i"),
                            url = u,
                            preview = null,
                            source = source,
                        )
                    }
                }
            }
        }.onFailure { /* ignore */ }
        // 进一步回退：旧 API 路径 /api/v3/comic/{slug}/chapter/{id}
        runCatching {
            val base = refreshAppApi()
            val altUrl2 = "https://$base/api/v3/comic/${chapter.branch}/chapter/${chapter.url}?platform=1&_update=true&line=1"
            val altResp2 = webClient.httpGet(altUrl2, getRequestHeaders())
            if (altResp2.code != 210) {
                val altData2 = altResp2.parseJson()
                val altChapter2 = altData2.optJSONObject("results")?.optJSONObject("chapter") ?: JSONObject()
                val altContents2 = altChapter2.optJSONArray("contents") ?: JSONArray()
                val altOrders2 = altChapter2.optJSONArray("words") ?: JSONArray()
                val altUrls2 = ArrayList<String>(altContents2.length())
                for (i in 0 until altContents2.length()) {
                    val item = altContents2.optJSONObject(i) ?: continue
                    val rawUrl = item.optString("url")
                    if (rawUrl.isNullOrEmpty()) continue
                    val hdUrl = rawUrl.replace(Regex("([./])c\\d+x\\.[a-zA-Z]+$"), "$1c${imageQuality}x.webp")
                    altUrls2 += hdUrl
                }
                if (altUrls2.isNotEmpty()) {
                    val pagesByOrder = MutableList(altUrls2.size) { "" }
                    for (i in altUrls2.indices) {
                        val pos = altOrders2.optInt(i, i)
                        if (pos in pagesByOrder.indices) pagesByOrder[pos] = altUrls2[i]
                    }
                    val ordered = pagesByOrder.mapIndexedNotNull { i, u ->
                        if (u.isEmpty()) null else MangaPage(
                            id = generateUid("${chapter.url}/$i"),
                            url = u,
                            preview = null,
                            source = source,
                        )
                    }
                    if (ordered.isNotEmpty()) return ordered
                    return altUrls2.mapIndexed { i, u ->
                        MangaPage(
                            id = generateUid("${chapter.url}/$i"),
                            url = u,
                            preview = null,
                            source = source,
                        )
                    }
                }
            }
        }.onFailure { /* ignore */ }
        // 达到最大重试次数或 API 不可用时，尝试 HTML 阅读页解析回退
        // HTML 回退需使用站点域（www.*），避免 api.* 域 404
        val siteDomain = siteDomain()
        val htmlUrl = "https://$siteDomain/comic/${chapter.branch}/chapter/${chapter.url}"
        val doc = webClient.httpGet(htmlUrl, getRequestHeaders()).parseHtml()
        val imgs = doc.select("img[data-src], img[src]")
        return imgs.mapNotNull { img ->
            val u = img.attr("data-src").ifEmpty { img.attr("src") }
            if (u.isNullOrEmpty()) null else MangaPage(
                id = generateUid(u),
                url = if (u.startsWith("http")) u else "https://$siteDomain$u",
                preview = null,
                source = source,
            )
        }
    }

    // 为图片请求设置站点 Referer/Origin，避免静态资源服务端返回 4xx/5xx
    override fun intercept(chain: Interceptor.Chain): Response {
        val req = chain.request()
        val accept = req.header("Accept").orEmpty()
        val url = req.url
        val host = url.host
        val path = url.encodedPath
        val isApiRequest = host.startsWith("api.") || path.contains("/api/")
        val hasImageExt = url.pathSegments.lastOrNull()?.let { seg ->
            seg.endsWith(".jpg", true) || seg.endsWith(".jpeg", true) || seg.endsWith(".png", true) || seg.endsWith(".webp", true) || seg.endsWith(".gif", true) || seg.endsWith(".avif", true) || seg.endsWith(".svg", true) || seg.endsWith(".ico", true)
        } == true
        val isImageRequest = accept.contains("image/") || hasImageExt
        val site = siteDomain()

        return if (isImageRequest) {
            val newReq = req.newBuilder()
                .header("Accept", "image/avif,image/webp,image/apng,image/png,image/*,*/*;q=0.8")
                .header("Accept-Language", "zh-CN,zh;q=0.9,en;q=0.8")
                .header("User-Agent", config[userAgentKey])
                .header("Referer", "https://$site/")
                .header("Origin", "https://$site")
                // 避免在图片请求上携带认证头导致服务端拒绝
                .removeHeader("Authorization")
                .removeHeader("authorization")
                // 清理 App 特征头，避免静态域安全校验拒绝
                .removeHeader("source")
                .removeHeader("deviceinfo")
                .removeHeader("platform")
                .removeHeader("version")
                .removeHeader("device")
                .removeHeader("pseudoid")
                .removeHeader("region")
                .removeHeader("dt")
                .removeHeader("x-auth-timestamp")
                .removeHeader("x-auth-signature")
                .removeHeader("umstring")
                .build()
            val resp = chain.proceed(newReq)
            val ct = resp.header("Content-Type").orEmpty()
            return if (ct.contains("octet-stream", ignoreCase = true)) {
                resp.newBuilder().header("Content-Type", "image/jpeg").build()
            } else resp
        } else if (!isApiRequest && req.method == "GET") {
            // 统一处理非 API 的 GET：放宽 Accept，设置 Referer/Origin，移除 App 特征与认证头
            val newReq = req.newBuilder()
                .header("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/avif,image/webp,image/apng,image/png,image/*,*/*;q=0.8")
                .header("Accept-Language", "zh-CN,zh;q=0.9,en;q=0.8")
                .header("User-Agent", config[userAgentKey])
                .header("Referer", "https://$site/")
                .header("Origin", "https://$site")
                .removeHeader("Authorization")
                .removeHeader("authorization")
                .removeHeader("source")
                .removeHeader("deviceinfo")
                .removeHeader("platform")
                .removeHeader("version")
                .removeHeader("device")
                .removeHeader("pseudoid")
                .removeHeader("region")
                .removeHeader("dt")
                .removeHeader("x-auth-timestamp")
                .removeHeader("x-auth-signature")
                .removeHeader("umstring")
                .build()
            val resp = chain.proceed(newReq)
            val ct = resp.header("Content-Type").orEmpty()
            return if (ct.contains("octet-stream", ignoreCase = true)) {
                resp.newBuilder().header("Content-Type", "image/jpeg").build()
            } else resp
        } else {
            chain.proceed(req)
        }
    }

    private fun generateDeviceInfo(): String {
        fun randInt(min: Int, max: Int): Int = Random.Default.nextInt(min, max + 1)
        return "${randInt(1000000, 9999999)}V-${randInt(1000, 9999)}"
    }

    private fun generateDevice(): String {
        fun randCharA(): Char = (Random.Default.nextInt('A'.code, 'Z'.code + 1)).toChar()
        fun randDigit(): Char = (Random.Default.nextInt('0'.code, '9'.code + 1)).toChar()
        return buildString {
            append(randCharA()); append(randCharA()); append(randDigit()); append(randCharA());
            append('.'); repeat(6) { append(randDigit()) }
            append('.'); repeat(3) { append(randDigit()) }
        }
    }

    private fun generatePseudoId(): String {
        val chars = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789"
        return buildString {
            repeat(16) { append(chars[Random.Default.nextInt(chars.length)]) }
        }
    }

    private fun hmacSha256(secretBase64: String, data: String): String {
        val secret = java.util.Base64.getDecoder().decode(secretBase64)
        val mac = javax.crypto.Mac.getInstance("HmacSHA256")
        val keySpec = javax.crypto.spec.SecretKeySpec(secret, "HmacSHA256")
        mac.init(keySpec)
        val bytes = mac.doFinal(data.toByteArray())
        return bytes.joinToString(separator = "") { "%02x".format(it) }
    }

    companion object {
        private const val DEFAULT_REGION = "1"
        private const val COPY_SECRET = "M2FmMDg1OTAzMTEwMzJlZmUwNjYwNTUwYTA1NjNhNTM="
        private const val COPY_SEARCH_API = "/api/kb/web/searchb/comics"
        private val REGEX_COUNT_API = Regex("const countApi = \"([^\"]+)\"")
        private val HAN_REGEX = Regex("[\\p{IsHan}]")
    }
}
