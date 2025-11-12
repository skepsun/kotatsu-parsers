package org.skepsun.kototoro.parsers.site.madara.en

import org.jsoup.nodes.Document
import org.skepsun.kototoro.parsers.MangaLoaderContext
import org.skepsun.kototoro.parsers.MangaSourceParser
import org.skepsun.kototoro.parsers.model.MangaChapter
import org.skepsun.kototoro.parsers.model.MangaParserSource
import org.skepsun.kototoro.parsers.site.madara.MadaraParser
import org.skepsun.kototoro.parsers.util.*
import java.text.SimpleDateFormat

@MangaSourceParser("MANHWATOP", "ManhwaTop", "en")
internal class ManhwaTop(context: MangaLoaderContext) :
	MadaraParser(context, MangaParserSource.MANHWATOP, "manhwatop.com") {

	override val postReq = true

	override suspend fun loadChapters(mangaUrl: String, document: Document): List<MangaChapter> {

		val mangaId = document.select("div#manga-chapters-holder").attr("data-id")
		val url = "https://$domain/wp-admin/admin-ajax.php"
		val postData = "action=manga_get_chapters&manga=$mangaId"
		val doc = webClient.httpPost(url, postData).parseHtml()

		val dateFormat = SimpleDateFormat(datePattern, sourceLocale)

		return doc.select(selectChapter).mapChapters(reversed = true) { i, li ->
			val a = li.selectFirst("a")
			val href = a?.attrAsRelativeUrlOrNull("href") ?: li.parseFailed("Link is missing")
			val link = href + stylePage
			val dateText = li.selectFirst("a.c-new-tag")?.attr("title") ?: li.selectFirst(selectDate)?.text()
			val name = a.selectFirst("p")?.text() ?: a.ownText()
			val dateText2 = if (dateText != "Complete") {
				dateText
			} else {
				null
			}
			MangaChapter(
				id = generateUid(href),
				url = link,
				title = name,
				number = i + 1f,
				volume = 0,
				branch = null,
				uploadDate = parseChapterDate(
					dateFormat,
					dateText2,
				),
				scanlator = null,
				source = source,
			)
		}
	}
}
