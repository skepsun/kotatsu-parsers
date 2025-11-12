package org.skepsun.kototoro.parsers.site.madara.all

import org.jsoup.nodes.Document
import org.skepsun.kototoro.parsers.MangaLoaderContext
import org.skepsun.kototoro.parsers.MangaSourceParser
import org.skepsun.kototoro.parsers.model.ContentType
import org.skepsun.kototoro.parsers.model.Manga
import org.skepsun.kototoro.parsers.model.MangaChapter
import org.skepsun.kototoro.parsers.model.MangaParserSource
import org.skepsun.kototoro.parsers.site.madara.MadaraParser
import org.skepsun.kototoro.parsers.util.attrAsRelativeUrl
import org.skepsun.kototoro.parsers.util.generateUid
import org.skepsun.kototoro.parsers.util.mapChapters
import org.skepsun.kototoro.parsers.util.selectFirstOrThrow
import java.text.SimpleDateFormat
import java.util.*

@MangaSourceParser("ERO18X", "Ero18x", "", ContentType.HENTAI)
internal class Ero18x(context: MangaLoaderContext) :
	MadaraParser(context, MangaParserSource.ERO18X, "ero18x.com", 10) {
	override val datePattern = "MM/dd"
	override val sourceLocale: Locale = Locale.ENGLISH
	override suspend fun getChapters(manga: Manga, doc: Document): List<MangaChapter> {
		val dateFormat = SimpleDateFormat(datePattern, sourceLocale)
		return doc.body().select(selectChapter).mapChapters(reversed = true) { i, li ->
			val a = li.selectFirstOrThrow("a")
			val href = a.attrAsRelativeUrl("href")
			val link = href + stylePage
			val dateText = li.selectFirst("a.c-new-tag")?.attr("title") ?: li.selectFirst(selectDate)?.text()
			val name = a.selectFirst("p")?.text() ?: a.ownText()
			MangaChapter(
				id = generateUid(href),
				title = name,
				number = i + 1f,
				volume = 0,
				url = link,
				uploadDate = if (dateText == "Newly Published!") {
					parseChapterDate(
						dateFormat,
						"today",
					)
				} else {
					parseChapterDate(
						dateFormat,
						dateText,
					)
				},
				source = source,
				scanlator = null,
				branch = null,
			)
		}
	}
}
