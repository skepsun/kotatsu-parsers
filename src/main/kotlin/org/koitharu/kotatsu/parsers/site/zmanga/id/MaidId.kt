package org.skepsun.kototoro.parsers.site.zmanga.id

import org.jsoup.nodes.Document
import org.skepsun.kototoro.parsers.MangaLoaderContext
import org.skepsun.kototoro.parsers.MangaSourceParser
import org.skepsun.kototoro.parsers.model.MangaChapter
import org.skepsun.kototoro.parsers.model.MangaParserSource
import org.skepsun.kototoro.parsers.site.zmanga.ZMangaParser
import org.skepsun.kototoro.parsers.util.attrAsRelativeUrl
import org.skepsun.kototoro.parsers.util.generateUid
import org.skepsun.kototoro.parsers.util.mapChapters
import org.skepsun.kototoro.parsers.util.selectFirstOrThrow
import java.text.SimpleDateFormat

// Info: Some scans are password-protected
@MangaSourceParser("MAID_ID", "MaidId", "id")
internal class MaidId(context: MangaLoaderContext) :
	ZMangaParser(context, MangaParserSource.MAID_ID, "www.maid.my.id") {

	override suspend fun getChapters(doc: Document): List<MangaChapter> {
		val dateFormat = SimpleDateFormat(datePattern, sourceLocale)
		return doc.body().select(selectChapter).mapChapters(reversed = true) { i, li ->
			val a = li.selectFirstOrThrow("a")
			val href = a.attrAsRelativeUrl("href")
			val dateText = li.selectFirst(selectDate)?.text()
			val numChapter = li.selectFirstOrThrow(".flexch-infoz span").html().substringAfterLast("Chapter ")
				.substringBefore("<span")
			MangaChapter(
				id = generateUid(href),
				title = null,
				number = i + 1f,
				volume = 0,
				url = href,
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
