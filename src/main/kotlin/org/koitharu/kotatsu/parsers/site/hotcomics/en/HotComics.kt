package org.skepsun.kototoro.parsers.site.hotcomics.en

import okhttp3.Headers
import org.skepsun.kototoro.parsers.MangaLoaderContext
import org.skepsun.kototoro.parsers.MangaSourceParser
import org.skepsun.kototoro.parsers.site.hotcomics.HotComicsParser
import org.skepsun.kototoro.parsers.model.*
import org.skepsun.kototoro.parsers.util.*
import java.text.SimpleDateFormat

@MangaSourceParser("HOTCOMICS", "HotComics", "en")
internal class HotComics(context: MangaLoaderContext) :
	HotComicsParser(context, MangaParserSource.HOTCOMICS, "hotcomics.me/en") {
	
	override suspend fun getDetails(manga: Manga): Manga {
		val mangaUrl = manga.url.toAbsoluteUrl(domain)
		val redirectHeaders = Headers.Builder().set("Referer", mangaUrl).build()
		val doc = webClient.httpGet(mangaUrl, redirectHeaders).parseHtml()
		val chapters = doc.select("#tab-chapter a").mapChapters { i, element ->
			val url = element.attr("onclick").substringAfter("popupLogin('").substringBefore("'")
			val name = element.selectFirst(".cell-num")?.text() ?: "Unknown"
			val dateFormat = SimpleDateFormat(datePattern, sourceLocale)
			val dateUpload = dateFormat.parseSafe(element.selectFirst(".cell-time")?.text())
			val chapterNum = element.selectFirst(".num")?.text()?.toFloat() ?: (i + 1f)
			MangaChapter(
				id = generateUid(url),
				title = name,
				number = chapterNum,
				volume = 0,
				url = url,
				scanlator = null,
				uploadDate = dateUpload,
				branch = null,
				source = source,
			)
		}

		return manga.copy(
			description = doc.selectFirst("div.title_content_box h2")?.text() ?: manga.description,
			chapters = chapters,
		)
	}
}
