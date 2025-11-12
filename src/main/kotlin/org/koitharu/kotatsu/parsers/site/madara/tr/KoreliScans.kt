package org.skepsun.kototoro.parsers.site.madara.tr

import org.skepsun.kototoro.parsers.MangaLoaderContext
import org.skepsun.kototoro.parsers.MangaSourceParser
import org.skepsun.kototoro.parsers.model.MangaParserSource
import org.skepsun.kototoro.parsers.site.madara.MadaraParser

@MangaSourceParser("KORELISCANS", "KoreliScans", "tr")
internal class KoreliScans(context: MangaLoaderContext) :
	MadaraParser(context, MangaParserSource.KORELISCANS, "koreliscans.com", 10) {
	override val datePattern = "d MMMM"
}
