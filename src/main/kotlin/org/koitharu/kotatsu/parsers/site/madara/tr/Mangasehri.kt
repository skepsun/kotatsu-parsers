package org.skepsun.kototoro.parsers.site.madara.tr

import org.skepsun.kototoro.parsers.MangaLoaderContext
import org.skepsun.kototoro.parsers.MangaSourceParser
import org.skepsun.kototoro.parsers.model.MangaParserSource
import org.skepsun.kototoro.parsers.site.madara.MadaraParser

@MangaSourceParser("MANGASEHRI", "MangaSehri.com", "tr")
internal class Mangasehri(context: MangaLoaderContext) :
	MadaraParser(context, MangaParserSource.MANGASEHRI, "manga-sehri.com", 18) {
	override val datePattern = "dd/MM/yyyy"
}
