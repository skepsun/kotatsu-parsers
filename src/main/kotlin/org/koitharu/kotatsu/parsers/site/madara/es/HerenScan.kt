package org.skepsun.kototoro.parsers.site.madara.es

import org.skepsun.kototoro.parsers.MangaLoaderContext
import org.skepsun.kototoro.parsers.MangaSourceParser
import org.skepsun.kototoro.parsers.model.MangaParserSource
import org.skepsun.kototoro.parsers.site.madara.MadaraParser

@MangaSourceParser("HERENSCAN", "HerenScan", "es")
internal class HerenScan(context: MangaLoaderContext) :
	MadaraParser(context, MangaParserSource.HERENSCAN, "herenscan.com") {
	override val datePattern = "dd/MM/yyyy"
}
