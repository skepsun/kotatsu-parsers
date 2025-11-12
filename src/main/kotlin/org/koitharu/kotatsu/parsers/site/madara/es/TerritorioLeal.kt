package org.skepsun.kototoro.parsers.site.madara.es

import org.skepsun.kototoro.parsers.MangaLoaderContext
import org.skepsun.kototoro.parsers.MangaSourceParser
import org.skepsun.kototoro.parsers.model.MangaParserSource
import org.skepsun.kototoro.parsers.site.madara.MadaraParser

@MangaSourceParser("TERRITORIOLEAL", "TerritorioLeal", "es")
internal class TerritorioLeal(context: MangaLoaderContext) :
	MadaraParser(context, MangaParserSource.TERRITORIOLEAL, "territorioleal.com") {
	override val datePattern = "d 'de' MMMM 'de' yyyy"
}
