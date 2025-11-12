package org.skepsun.kototoro.parsers.site.madara.es

import org.skepsun.kototoro.parsers.MangaLoaderContext
import org.skepsun.kototoro.parsers.MangaSourceParser
import org.skepsun.kototoro.parsers.model.MangaParserSource
import org.skepsun.kototoro.parsers.site.madara.MadaraParser
import java.util.*

@MangaSourceParser("RICHTOSCAN", "RichtoScan", "es")
internal class RichtoScan(context: MangaLoaderContext) :
	MadaraParser(context, MangaParserSource.RICHTOSCAN, "r1.richtoon.top") {
	override val tagPrefix = "manga-generos/"
	override val sourceLocale: Locale = Locale.ENGLISH
}
