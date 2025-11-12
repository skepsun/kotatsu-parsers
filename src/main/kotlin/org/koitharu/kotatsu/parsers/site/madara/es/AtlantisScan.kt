package org.skepsun.kototoro.parsers.site.madara.es

import org.skepsun.kototoro.parsers.Broken
import org.skepsun.kototoro.parsers.MangaLoaderContext
import org.skepsun.kototoro.parsers.MangaSourceParser
import org.skepsun.kototoro.parsers.model.MangaParserSource
import org.skepsun.kototoro.parsers.site.madara.MadaraParser

@Broken
@MangaSourceParser("ATLANTISSCAN", "AtlantisScan", "es")
internal class AtlantisScan(context: MangaLoaderContext) :
	MadaraParser(context, MangaParserSource.ATLANTISSCAN, "scansatlanticos.com", pageSize = 50) {
	override val datePattern = "dd/MM/yyyy"
}
