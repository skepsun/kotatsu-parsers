package org.skepsun.kototoro.parsers.site.madara.tr

import org.jsoup.nodes.Document
import org.skepsun.kototoro.parsers.MangaLoaderContext
import org.skepsun.kototoro.parsers.MangaSourceParser
import org.skepsun.kototoro.parsers.model.MangaChapter
import org.skepsun.kototoro.parsers.model.MangaParserSource
import org.skepsun.kototoro.parsers.site.madara.MadaraParser
import org.skepsun.kototoro.parsers.util.*

@MangaSourceParser("TITANMANGA", "TitanManga", "tr")
internal class TitanManga(context: MangaLoaderContext) :
	MadaraParser(context, MangaParserSource.TITANMANGA, "titanmanga.com") {

	override suspend fun loadChapters(mangaUrl: String, document: Document): List<MangaChapter> {
		val url = mangaUrl.toAbsoluteUrl(domain).removeSuffix('/') + "/ajax/chapters/"
		val doc = webClient.httpPost(url, emptyMap()).parseHtml()
		return doc.select(selectChapter).mapChapters(reversed = true) { i, li ->
			val a = li.selectFirstOrThrow("a")
			val href = a.attrAsRelativeUrl("href")
			val link = href + stylePage
			val name = a.selectFirst("p")?.text() ?: a.ownText()
			MangaChapter(
				id = generateUid(href),
				url = link,
				title = name,
				number = i + 1f,
				volume = 0,
				branch = null,
				uploadDate = 0,
				scanlator = null,
				source = source,
			)
		}
	}
}
