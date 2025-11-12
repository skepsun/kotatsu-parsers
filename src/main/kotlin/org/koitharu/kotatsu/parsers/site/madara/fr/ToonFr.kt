package org.skepsun.kototoro.parsers.site.madara.fr

import org.jsoup.nodes.Document
import org.skepsun.kototoro.parsers.MangaLoaderContext
import org.skepsun.kototoro.parsers.MangaSourceParser
import org.skepsun.kototoro.parsers.model.ContentType
import org.skepsun.kototoro.parsers.model.MangaChapter
import org.skepsun.kototoro.parsers.model.MangaParserSource
import org.skepsun.kototoro.parsers.site.madara.MadaraParser
import org.skepsun.kototoro.parsers.util.*
import java.text.SimpleDateFormat

@MangaSourceParser("TOONFR", "ToonFr", "fr", ContentType.HENTAI)
internal class ToonFr(context: MangaLoaderContext) :
	MadaraParser(context, MangaParserSource.TOONFR, "toonfr.com") {

	override val tagPrefix = "webtoon-genre/"
	override val listUrl = "webtoon/"
	override val datePattern = "MMM d"

	override suspend fun loadChapters(mangaUrl: String, document: Document): List<MangaChapter> {
		val url = mangaUrl.toAbsoluteUrl(domain).removeSuffix('/') + "/ajax/chapters/"
		val dateFormat = SimpleDateFormat(datePattern, sourceLocale)
		val doc = webClient.httpPost(url, emptyMap()).parseHtml()
		return doc.select("li.wp-manga-chapter").mapChapters(reversed = true) { i, li ->
			val a = li.selectFirstOrThrow("a")
			val href = a.attrAsRelativeUrl("href") + "?style=list"
			// correct parse date missing a "."
			val dateText = li.selectFirst("span.chapter-release-date i")?.text()
			val dateCorrectParse = dateReplace(dateText ?: "janv 1, 1970")
			MangaChapter(
				id = generateUid(href),
				url = href,
				title = a.text(),
				number = i + 1f,
				volume = 0,
				branch = null,
				uploadDate = parseChapterDate(
					dateFormat,
					dateCorrectParse,
				),
				scanlator = null,
				source = source,
			)
		}
	}

	private fun dateReplace(date: String): String {
		return date.lowercase()
			.replace("jan", "janv.")
			.replace("fév", "févr.")
			.replace("mar", "mars")
			.replace("avr", "avr.")
			.replace("juil", "juil.")
			.replace("sep", "sept.")
			.replace("nov", "nov.")
			.replace("oct", "oct.")
			.replace("déc", "déc.")
	}
}

