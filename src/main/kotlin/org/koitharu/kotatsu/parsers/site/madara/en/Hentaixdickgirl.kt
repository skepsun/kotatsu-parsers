package org.skepsun.kototoro.parsers.site.madara.en

import org.jsoup.nodes.Document
import org.skepsun.kototoro.parsers.MangaLoaderContext
import org.skepsun.kototoro.parsers.MangaSourceParser
import org.skepsun.kototoro.parsers.model.ContentType
import org.skepsun.kototoro.parsers.model.Manga
import org.skepsun.kototoro.parsers.model.MangaChapter
import org.skepsun.kototoro.parsers.model.MangaParserSource
import org.skepsun.kototoro.parsers.site.madara.MadaraParser
import org.skepsun.kototoro.parsers.util.*
import java.text.SimpleDateFormat

@MangaSourceParser("HENTAIXDICKGIRL", "Hentai x Dickgirl", "en", ContentType.HENTAI)
internal class Hentaixdickgirl(context: MangaLoaderContext) :
	MadaraParser(context, MangaParserSource.HENTAIXDICKGIRL, "hentaixdickgirl.com", 16) {
	override val postReq = true

	override suspend fun getChapters(manga: Manga, doc: Document): List<MangaChapter> {
		val root2 = doc.body().selectFirstOrThrow("div.listing-chapters_wrap")
		val dateFormat = SimpleDateFormat(datePattern, sourceLocale)
		return root2.select(selectChapter).mapChapters(reversed = true) { i, li ->
			val a = li.selectFirst("a")
			val href = a?.attrAsRelativeUrlOrNull("href") ?: li.parseFailed("Link is missing")
			val link = href + stylePage
			val dateText = li.selectFirst("a.c-new-tag")?.attr("title") ?: li.selectFirst(selectDate)?.text()
			val name = a.selectFirst("p")?.text() ?: a.ownText()
			MangaChapter(
				id = generateUid(href),
				title = name,
				number = i + 1f,
				volume = 0,
				url = link,
				uploadDate = parseChapterDate(
					dateFormat,
					dateText,
				),
				source = source,
				scanlator = null,
				branch = null,
			)
		}
	}
}
