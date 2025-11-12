package org.skepsun.kototoro.parsers.site.madara.es

import org.skepsun.kototoro.parsers.Broken
import org.skepsun.kototoro.parsers.MangaLoaderContext
import org.skepsun.kototoro.parsers.MangaSourceParser
import org.skepsun.kototoro.parsers.model.MangaParserSource
import org.skepsun.kototoro.parsers.site.madara.MadaraParser

@Broken
@MangaSourceParser("PANCONCOLA", "Panconcola", "es")
internal class Panconcola(context: MangaLoaderContext) :
	MadaraParser(context, MangaParserSource.PANCONCOLA, "artessupremas.com") {
	override val datePattern = "dd/MM/yyyy"
	override val tagPrefix = "generos-de-manga/"
}
