package org.skepsun.kototoro.parsers.site.mangareader.fr

import org.skepsun.kototoro.parsers.MangaLoaderContext
import org.skepsun.kototoro.parsers.MangaSourceParser
import org.skepsun.kototoro.parsers.model.MangaChapter
import org.skepsun.kototoro.parsers.model.MangaPage
import org.skepsun.kototoro.parsers.model.MangaParserSource
import org.skepsun.kototoro.parsers.site.mangareader.MangaReaderParser
import org.skepsun.kototoro.parsers.util.*
import java.util.*

@MangaSourceParser("LELMANGA", "LelManga", "fr")
internal class LelManga(context: MangaLoaderContext) :
	MangaReaderParser(context, MangaParserSource.LELMANGA, "www.lelmanga.com", pageSize = 21, searchPageSize = 20) {

	override val sourceLocale: Locale = Locale.ENGLISH

	override suspend fun getPages(chapter: MangaChapter): List<MangaPage> {
		val fullUrl = chapter.url.toAbsoluteUrl(domain)
		val doc = webClient.httpGet(fullUrl).parseHtml()
		val root = doc.body().selectFirstOrThrow("div.maincontent").requireElementById("readerarea")
		return root.select("img").map { img ->
			val url = img.requireSrc().toRelativeUrl(domain)
			MangaPage(
				id = generateUid(url),
				url = url,
				preview = null,
				source = source,
			)
		}
	}
}
