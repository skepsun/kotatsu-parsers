package org.skepsun.kototoro.parsers.site.madara.pt

import org.skepsun.kototoro.parsers.Broken
import org.skepsun.kototoro.parsers.MangaLoaderContext
import org.skepsun.kototoro.parsers.MangaSourceParser
import org.skepsun.kototoro.parsers.model.MangaParserSource
import org.skepsun.kototoro.parsers.site.madara.MadaraParser

@Broken
@MangaSourceParser("CABAREDOWATAME", "DessertScan", "pt")
internal class Cabaredowatame(context: MangaLoaderContext) :
	MadaraParser(context, MangaParserSource.CABAREDOWATAME, "cabaredowatame.site", 10) {
	override val datePattern = "dd/MM/yyyy"
}
