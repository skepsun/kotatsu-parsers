package org.skepsun.kototoro.parsers.site.madara.th

import org.skepsun.kototoro.parsers.MangaLoaderContext
import org.skepsun.kototoro.parsers.MangaSourceParser
import org.skepsun.kototoro.parsers.model.ContentType
import org.skepsun.kototoro.parsers.model.MangaParserSource
import org.skepsun.kototoro.parsers.site.madara.MadaraParser

@MangaSourceParser("DOUJINZA", "Doujinza", "th", ContentType.HENTAI)
internal class Doujinza(context: MangaLoaderContext) :
	MadaraParser(context, MangaParserSource.DOUJINZA, "doujinza.com", 24) {
	override val withoutAjax = true
	override val datePattern = "MMMM dd, yyyy"
	override val listUrl = "doujin/"
	override val tagPrefix = "doujin-genre/"
}
