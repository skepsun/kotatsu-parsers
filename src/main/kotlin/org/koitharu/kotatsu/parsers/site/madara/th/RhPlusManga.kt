package org.skepsun.kototoro.parsers.site.madara.th

import org.skepsun.kototoro.parsers.MangaLoaderContext
import org.skepsun.kototoro.parsers.MangaSourceParser
import org.skepsun.kototoro.parsers.model.MangaChapter
import org.skepsun.kototoro.parsers.model.MangaPage
import org.skepsun.kototoro.parsers.model.MangaParserSource
import org.skepsun.kototoro.parsers.site.madara.MadaraParser
import org.skepsun.kototoro.parsers.util.*
import org.skepsun.kototoro.parsers.Broken

@Broken
@MangaSourceParser("RHPLUSMANGA", "Rh2PlusManga", "th")
internal class RhPlusManga(context: MangaLoaderContext) :
	MadaraParser(context, MangaParserSource.RHPLUSMANGA, "www.rh2plusmanga.com") {

	override val datePattern: String = "d MMMM yyyy"

	override suspend fun getPages(chapter: MangaChapter): List<MangaPage> {
		val fullUrl = chapter.url.toAbsoluteUrl(domain)
		val doc = webClient.httpGet(fullUrl).parseHtml()
		val root = doc.body().selectFirstOrThrow("div.main-col-inner").selectFirstOrThrow("div.reading-content")
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
