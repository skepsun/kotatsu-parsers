package org.skepsun.kototoro.parsers.site.madara.en

import org.skepsun.kototoro.parsers.MangaLoaderContext
import org.skepsun.kototoro.parsers.MangaSourceParser
import org.skepsun.kototoro.parsers.model.MangaParserSource
import org.skepsun.kototoro.parsers.site.madara.MadaraParser

@MangaSourceParser("MANGALEVELING", "MangaLeveling", "en")
internal class MangaLeveling(context: MangaLoaderContext) :
	MadaraParser(context, MangaParserSource.MANGALEVELING, "mangaleveling.com", 30) {
	override val postReq = true
	override val tagPrefix = "comics-genre/"
	override val listUrl = "comics/"
	override val datePattern = "MM/dd/yyyy"
}
