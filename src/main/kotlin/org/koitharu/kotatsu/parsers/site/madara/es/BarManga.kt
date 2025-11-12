package org.skepsun.kototoro.parsers.site.madara.es

import org.skepsun.kototoro.parsers.MangaLoaderContext
import org.skepsun.kototoro.parsers.MangaSourceParser
import org.skepsun.kototoro.parsers.model.MangaParserSource
import org.skepsun.kototoro.parsers.site.madara.MadaraParser

@MangaSourceParser("BARMANGA", "BarManga", "es")
internal class BarManga(context: MangaLoaderContext) :
	MadaraParser(context, MangaParserSource.BARMANGA, "barmanga.com") {
	override val datePattern = "MM/dd/yyyy"
}
