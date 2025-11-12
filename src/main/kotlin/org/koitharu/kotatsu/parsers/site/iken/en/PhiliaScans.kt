package org.skepsun.kototoro.parsers.site.iken.en

import org.skepsun.kototoro.parsers.MangaLoaderContext
import org.skepsun.kototoro.parsers.MangaSourceParser
import org.skepsun.kototoro.parsers.model.MangaParserSource
import org.skepsun.kototoro.parsers.site.iken.IkenParser
import org.skepsun.kototoro.parsers.model.*
import org.skepsun.kototoro.parsers.util.*

@MangaSourceParser("PHILIASCANS", "PhiliaScans", "en")
internal class PhiliaScans(context: MangaLoaderContext) :
	IkenParser(context, MangaParserSource.PHILIASCANS, "philiascans.org", 18) {

	override suspend fun getPages(chapter: MangaChapter): List<MangaPage> {
		val fullUrl = chapter.url.toAbsoluteUrl(domain)
		val doc = webClient.httpGet(fullUrl).parseHtml()
		return doc.select(selectPages).map { img ->
			val url = img.requireSrc()
			MangaPage(
				id = generateUid(url),
				url = url,
				preview = null,
				source = source,
			)
		}
	}
}