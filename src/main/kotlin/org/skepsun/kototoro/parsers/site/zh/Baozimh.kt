package org.skepsun.kototoro.parsers.site.zh

import androidx.collection.ArrayMap
import okhttp3.Headers
import org.json.JSONArray
import org.jsoup.nodes.Document
import org.skepsun.kototoro.parsers.FavoritesProvider
import org.skepsun.kototoro.parsers.MangaLoaderContext
import org.skepsun.kototoro.parsers.MangaParserAuthProvider
import org.skepsun.kototoro.parsers.MangaParserCredentialsAuthProvider
import org.skepsun.kototoro.parsers.FavoritesSyncProvider
import org.skepsun.kototoro.parsers.MangaSourceParser
import org.skepsun.kototoro.parsers.config.ConfigKey
import org.skepsun.kototoro.parsers.core.PagedMangaParser
import org.skepsun.kototoro.parsers.exception.AuthRequiredException
import org.skepsun.kototoro.parsers.exception.ParseException
import org.skepsun.kototoro.parsers.model.*
import org.skepsun.kototoro.parsers.network.CloudFlareHelper
import org.skepsun.kototoro.parsers.network.UserAgents
import org.skepsun.kototoro.parsers.util.*
import org.skepsun.kototoro.parsers.util.getCookies
import org.skepsun.kototoro.parsers.util.insertCookies
import org.skepsun.kototoro.parsers.util.parseJson
import okhttp3.HttpUrl.Companion.toHttpUrl
import okhttp3.Request
import okhttp3.MultipartBody
import org.skepsun.kototoro.parsers.util.await
import org.skepsun.kototoro.parsers.util.json.mapJSON
import org.skepsun.kototoro.parsers.util.suspendlazy.suspendLazy
import java.util.*

@MangaSourceParser("BAOZIMH", "包子漫画", "zh")
internal class Baozimh(context: MangaLoaderContext) :
	PagedMangaParser(context, MangaParserSource.BAOZIMH, pageSize = 36),
	MangaParserAuthProvider,
	MangaParserCredentialsAuthProvider,
	FavoritesProvider,
	FavoritesSyncProvider {

	override val configKeyDomain = ConfigKey.Domain(
		"bzmgcn.com",
		"baozimhcn.com",
		"webmota.com",
		"kukuc.co",
		"twmanga.com",
		"baozimh.com",
	)

	private val lang: String get() = "cn"

	private val baseUrl: String get() {
		val d = super.domain.removePrefix("www.")
		return if (d.startsWith("cn.") || d.startsWith("tw.")) d else "$lang.$d"
	}

	override val userAgentKey = ConfigKey.UserAgent("Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/143.0.0.0 Safari/537.36 Edg/143.0.0.0")

	override fun onCreateConfig(keys: MutableCollection<ConfigKey<*>>) {
		super.onCreateConfig(keys)
		keys.add(userAgentKey)
	}

	override fun getRequestHeaders(): Headers = super.getRequestHeaders().newBuilder()
		.add("Sec-CH-UA", "\"Microsoft Edge\";v=\"143\", \"Chromium\";v=\"143\", \"Not A(Brand\";v=\"24\"")
		.add("Sec-CH-UA-Mobile", "?0")
		.add("Sec-CH-UA-Platform", "\"macOS\"")
		.build()

	override val availableSortOrders: Set<SortOrder> = EnumSet.of(SortOrder.POPULARITY)

	override val filterCapabilities: MangaListFilterCapabilities
		get() = MangaListFilterCapabilities(
			isSearchSupported = true,
		)

	override suspend fun getFilterOptions() = MangaListFilterOptions(
		availableTags = tagsMap.get().values.toSet(),
		availableStates = EnumSet.of(MangaState.ONGOING, MangaState.FINISHED),
		availableContentTypes = EnumSet.of(
			ContentType.MANGA,
			ContentType.MANHWA,
			ContentType.MANHUA,
			ContentType.COMICS,
		),
	)

	private val tagsMap = suspendLazy(initializer = ::parseTags)

	override suspend fun getListPage(page: Int, order: SortOrder, filter: MangaListFilter): List<Manga> {
		when {
			!filter.query.isNullOrEmpty() -> {
				if (page > 1) return emptyList()
				val url = buildString {
					append("https://")
					append(baseUrl)
					append("/search?q=")
					append(filter.query.urlEncoded())
				}
				val response = webClient.httpGet(url)
				if (CloudFlareHelper.checkResponseForProtection(response) != CloudFlareHelper.PROTECTION_NOT_DETECTED) {
					context.requestBrowserAction(this, url)
				}
				return parseMangaListSearch(response.parseHtml())
			}

			else -> {
				val url = buildString {
					append("https://")
					append(baseUrl)
					append("/api/bzmhq/amp_comic_list?filter=*&region=")

					if (filter.types.isNotEmpty()) {
						filter.types.oneOrThrowIfMany().let {
							append(
								when (it) {
									ContentType.MANGA -> "jp"
									ContentType.MANHWA -> "kr"
									ContentType.MANHUA -> "cn"
									ContentType.COMICS -> "en"
									else -> "all"
								},
							)
						}
					} else append("all")


					append("&type=")
					if (filter.tags.isNotEmpty()) {
						filter.tags.oneOrThrowIfMany()?.let {
							append(it.key)
						}
					} else append("all")

					append("&state=")
					if (filter.states.isNotEmpty()) {
						filter.states.oneOrThrowIfMany()?.let {
							append(
								when (it) {
									MangaState.ONGOING -> "serial"
									MangaState.FINISHED -> "pub"
									else -> "all"
								},
							)
						}
					} else append("all")

					append("&limit=36&page=")
					append(page.toString())
				}

				val response = webClient.httpGet(url)
				if (CloudFlareHelper.checkResponseForProtection(response) != CloudFlareHelper.PROTECTION_NOT_DETECTED) {
					context.requestBrowserAction(this, url)
				}
				return parseMangaList(response.parseJson().getJSONArray("items"))
			}
		}
	}

	private fun parseMangaList(json: JSONArray): List<Manga> {
		return json.mapJSON { j ->
			val href = "https://$baseUrl/comic/" + j.getString("comic_id")
			val author = j.getString("author")
			Manga(
				id = generateUid(href),
				url = href,
				publicUrl = href,
				coverUrl = "https://static-tw.baozimh.com/cover/" + j.getString("topic_img"),
				title = j.getString("name"),
				altTitles = emptySet(),
				rating = RATING_UNKNOWN,
				tags = emptySet(),
				authors = setOfNotNull(author),
				state = null,
				source = source,
				contentRating = if (isNsfwSource) ContentRating.ADULT else null,
			)
		}
	}

	private fun parseMangaListSearch(doc: Document): List<Manga> {
		return doc.select("div.comics-card").map { div ->
			val href = "https://$baseUrl" + div.selectFirstOrThrow("a").attrAsRelativeUrl("href")
			Manga(
				id = generateUid(href),
				url = href,
				publicUrl = href,
				coverUrl = div.selectFirst("amp-img")?.src().orEmpty(),
				title = div.selectFirst(".comics-card__title h3")?.text().orEmpty(),
				altTitles = emptySet(),
				rating = RATING_UNKNOWN,
				tags = emptySet(),
				authors = emptySet(),
				state = null,
				source = source,
				contentRating = if (isNsfwSource) ContentRating.ADULT else null,
			)
		}
	}

	private suspend fun parseTags(): Map<String, MangaTag> {
		val tagElements = webClient.httpGet("https://$baseUrl/classify").parseHtml()
			.select("div.nav")[3].select("a.item:not(.active)")
		val tagMap = ArrayMap<String, MangaTag>(tagElements.size)
		for (el in tagElements) {
			val name = el.text()
			if (name.isEmpty()) continue
			tagMap[name] = MangaTag(
				key = el.attr("href").substringAfter("type=").substringBefore("&"),
				title = name,
				source = source,
			)
		}
		return tagMap
	}

	override suspend fun getDetails(manga: Manga): Manga {
		val url = manga.url.toAbsoluteUrl(baseUrl)
		val response = webClient.httpGet(url)
		if (CloudFlareHelper.checkResponseForProtection(response) != CloudFlareHelper.PROTECTION_NOT_DETECTED) {
			context.requestBrowserAction(this, url)
		}
		val doc = response.parseHtml()
		val state = doc.selectFirst(".tag-list span.tag")?.text()
		val tagMap = tagsMap.get()
		val selectTag = doc.select(".tag-list span.tag").drop(1)
		val tags = selectTag.mapNotNullToSet { tagMap[it.text()] }
		var chaptersReversed = false
		val chapters = try {
			doc.requireElementById("chapter-items")
				.select("div.comics-chapters a") + doc.requireElementById("chapters_other_list")
				.select("div.comics-chapters a")
		} catch (e: ParseException) {
			chaptersReversed = true
			// If the above fails it means the manga is new, so we select the chapters using the "comics-chapters__item" query
			doc.select(".comics-chapters__item")
		}
		return manga.copy(
			description = doc.selectFirst(".comics-detail__desc")?.text().orEmpty(),
			state = when (state) {
				"連載中" -> MangaState.ONGOING
				"已完結" -> MangaState.FINISHED
				else -> null
			},
			tags = tags,
			chapters = chapters.mapChapters(chaptersReversed) { i, a ->
				val url = a.attrAsRelativeUrl("href").toAbsoluteUrl(baseUrl)
				MangaChapter(
					id = generateUid(url),
					title = a.selectFirst("span")?.textOrNull(),
					number = i + 1f,
					volume = 0,
					url = url,
					scanlator = null,
					uploadDate = 0,
					branch = null,
					source = source,
				)
			},
		)
	}

	override suspend fun getPages(chapter: MangaChapter): List<MangaPage> {
		// 使用 App 版链接获取内容，更高效且包含原图
		// 格式类似：https://appcn.baozimh.com/baozimhapp/comic/chapter/{comicId}/{section}_{chapter}.html
		val comicId = when {
			chapter.url.contains("/comic/") -> {
				chapter.url.substringAfterLast("/comic/").substringBefore("/")
			}
			else -> {
				chapter.url.substringAfter("comic_id=").substringBefore("&")
			}
		}.ifEmpty { throw ParseException("缺少 comic_id", chapter.url) }

		val sectionSlot = chapter.url.substringAfter("section_slot=", "").substringBefore("&").ifEmpty { "0" }
		val chapterSlot = chapter.url.substringAfter("chapter_slot=", "").substringBefore("&").ifEmpty {
			// 兼容旧格式：/0_{epId}.html
			chapter.url.substringAfterLast("/").substringBefore(".html").removePrefix("0_")
		}
		val epId = "${sectionSlot}_${chapterSlot}"

		val appUrl = "https://appcn.baozimh.com/baozimhapp/comic/chapter/$comicId/$epId.html"

		val response = webClient.httpGet(
			url = appUrl,
			extraHeaders = Headers.headersOf(
				"Referer", "https://$baseUrl/",
				"Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/avif,image/webp,image/apng,*/*;q=0.8,application/signed-exchange;v=b3;q=0.7",
				"Sec-CH-UA", "\"Microsoft Edge\";v=\"143\", \"Chromium\";v=\"143\", \"Not A(Brand\";v=\"24\"",
				"Sec-CH-UA-Mobile", "?0",
				"Sec-CH-UA-Platform", "\"macOS\"",
				"Host", "appcn.baozimh.com"
			)
		)
		if (CloudFlareHelper.checkResponseForProtection(response) != CloudFlareHelper.PROTECTION_NOT_DETECTED) {
			context.requestBrowserAction(this, appUrl)
		}
		val doc = response.parseHtml()
		val imageNodes = doc.select(".comic-contain .chapter-img")
		
		return imageNodes.mapNotNull { node ->
			val imgUrl = node.selectFirst(".comic-contain__item")?.attrOrNull("data-src") ?: return@mapNotNull null
			
			// 1. 替换 /w640/ 为 / 以获取原图
			var processedUrl = imgUrl.replace("/w640/", "/")
			// 2. 补全协议
			if (processedUrl.startsWith("//")) {
				processedUrl = "https:$processedUrl"
			} else if (!processedUrl.startsWith("http")) {
				processedUrl = "https://$baseUrl$processedUrl"
			}
			
			MangaPage(
				id = generateUid(processedUrl),
				url = processedUrl,
				preview = null,
				source = source,
			)
		}
	}

	override val authUrl: String = "https://$baseUrl/user/login"

	override suspend fun isAuthorized(): Boolean {
		return context.cookieJar.getCookies(baseUrl).any { it.name == "TSID" }
	}

	override suspend fun getUsername(): String {
		if (!isAuthorized()) throw AuthRequiredException(source)
		return "User"
	}

	override suspend fun login(username: String, password: String): Boolean {
		val url = "https://$baseUrl/api/bui/signin"
		val body = MultipartBody.Builder()
			.setType(MultipartBody.FORM)
			.addFormDataPart("username", username)
			.addFormDataPart("password", password)
			.build()
		
		val request = Request.Builder()
			.url(url)
			.post(body)
			.headers(getRequestHeaders())
			.build()

		val response = try {
			context.httpClient.newCall(request).await()
		} catch (e: Exception) {
			return false
		}
		
		val json = response.parseJson()
		val token = json.optString("data")
		if (!token.isNullOrEmpty()) {
			context.cookieJar.insertCookies(baseUrl, "TSID=$token; Domain=$baseUrl; Path=/; HttpOnly")
			return true
		}
		return false
	}

	override suspend fun fetchFavorites(): List<Manga> {
		if (!isAuthorized()) throw AuthRequiredException(source)
		val headers = Headers.Builder()
			.add("User-Agent", config[userAgentKey])
			.add("referer", "https://$baseUrl/")
			.build()
		val url = "https://$baseUrl/user/my_bookshelf"
		val resp = webClient.httpGet(url, headers)
		if (resp.code == 401) throw AuthRequiredException(source)
		if (!resp.isSuccessful) return emptyList()
		val doc = resp.parseHtml()
		val items = doc.select("div.bookshelf-items")
		if (items.isEmpty()) return emptyList()
		return items.mapNotNull { el ->
			val link = el.selectFirst("h4 > a") ?: return@mapNotNull null
			val href = link.attr("href")
			val id = href.substringAfterLast('/').substringBefore('?').substringBefore("#").takeIf { it.isNotBlank() } ?: return@mapNotNull null
			val title = link.text().trim()
			val infoList = el.selectFirst("div.info > ul")
			val author = infoList?.children()?.getOrNull(1)?.text()?.substringAfter("：")?.trim().orEmpty()
			val desc = infoList?.children()?.getOrNull(4)?.children()?.firstOrNull()?.text()?.trim().orEmpty()
			val cover = el.selectFirst("amp-img")?.attr("src")
				?: el.selectFirst("img")?.let { img ->
					img.attr("data-src").ifEmpty { img.attr("src") }
				}
			val absCover = cover?.let { if (it.startsWith("http")) it else "https:$it" }
			Manga(
				id = generateUid(id),
				url = "/comic/$id",
				publicUrl = "https://$baseUrl/comic/$id",
				coverUrl = absCover,
				title = title,
				altTitles = emptySet(),
				rating = RATING_UNKNOWN,
				tags = emptySet(),
				authors = if (author.isNotEmpty()) setOf(author) else emptySet(),
				state = null,
				description = desc.ifEmpty { null },
				source = source,
				contentRating = ContentRating.SAFE,
			)
		}
	}

	override suspend fun addFavorite(manga: Manga): Boolean {
		if (!isAuthorized()) throw AuthRequiredException(source)
		val headers = Headers.Builder()
			.add("User-Agent", config[userAgentKey])
			.add("referer", "https://$baseUrl/")
			.build()
		val url = "https://$baseUrl/user/operation_v2?op=set_bookmark&comic_id=${manga.url}&chapter_slot=0"
		val resp = webClient.httpPost(url.toHttpUrl(), emptyMap<String, String>(), headers)
		if (resp.code == 401) throw AuthRequiredException(source)
		return resp.isSuccessful
	}

	override suspend fun removeFavorite(manga: Manga): Boolean {
		if (!isAuthorized()) throw AuthRequiredException(source)
		val headers = Headers.Builder()
			.add("User-Agent", config[userAgentKey])
			.add("referer", "https://$baseUrl/")
			.build()
		val url = "https://$baseUrl/user/operation_v2?op=del_bookmark&comic_id=${manga.url}"
		val resp = webClient.httpPost(url.toHttpUrl(), emptyMap<String, String>(), headers)
		if (resp.code == 401) throw AuthRequiredException(source)
		return resp.isSuccessful
	}
}
