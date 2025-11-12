package org.skepsun.kototoro.parsers.site.madara.tr

import org.skepsun.kototoro.parsers.Broken
import org.skepsun.kototoro.parsers.MangaLoaderContext
import org.skepsun.kototoro.parsers.MangaSourceParser
import org.skepsun.kototoro.parsers.model.MangaParserSource
import org.skepsun.kototoro.parsers.site.madara.MadaraParser

@Broken
@MangaSourceParser("KEDI", "Kedi", "tr")
internal class Kedi(context: MangaLoaderContext) :
	MadaraParser(context, MangaParserSource.KEDI, "kedi.to") {
	override val datePattern = "d MMMM yyyy"
	override val tagPrefix = "tur/"
}
