package org.skepsun.kototoro.parsers.site.madara.en

import org.skepsun.kototoro.parsers.MangaLoaderContext
import org.skepsun.kototoro.parsers.MangaSourceParser
import org.skepsun.kototoro.parsers.model.MangaParserSource
import org.skepsun.kototoro.parsers.site.madara.MadaraParser

@MangaSourceParser("MANGACLASH", "ToonClash", "en")
internal class Mangaclash(context: MangaLoaderContext) :
	MadaraParser(context, MangaParserSource.MANGACLASH, "toonclash.com", pageSize = 18) {
	override val datePattern = "MM/dd/yyyy"
}
