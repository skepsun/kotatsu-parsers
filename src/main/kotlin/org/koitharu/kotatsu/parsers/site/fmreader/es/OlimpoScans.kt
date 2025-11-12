package org.skepsun.kototoro.parsers.site.fmreader.es

import org.skepsun.kototoro.parsers.MangaLoaderContext
import org.skepsun.kototoro.parsers.MangaSourceParser
import org.skepsun.kototoro.parsers.model.*
import org.skepsun.kototoro.parsers.site.fmreader.FmreaderParser
import org.skepsun.kototoro.parsers.util.*

@MangaSourceParser("OLIMPOSCANS", "OlimpoScans", "es")
internal class OlimpoScans(context: MangaLoaderContext) :
	FmreaderParser(context, MangaParserSource.OLIMPOSCANS, "leerolimpo.com") {

	override val selectState = "ul.manga-info li:contains(Estado) a"
	override val selectAlt = "ul.manga-info li:contains(Otros nombres)"
	override val selectTag = "ul.manga-info li:contains(Género) a"
	override val tagPrefix = "lista-de-comics-genero-"

	override suspend fun getPages(chapter: MangaChapter): List<MangaPage> {
		val fullUrl = ("/" + chapter.url).toAbsoluteUrl(domain)
		val doc = webClient.httpGet(fullUrl).parseHtml()
		return doc.select(selectPage).map { img ->
			val url = ("/proxy.php?link=" + img.requireSrc()).toRelativeUrl(domain)
			MangaPage(
				id = generateUid(url),
				url = url,
				preview = null,
				source = source,
			)
		}
	}
}
