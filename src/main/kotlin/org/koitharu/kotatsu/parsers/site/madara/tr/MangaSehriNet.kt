package org.skepsun.kototoro.parsers.site.madara.tr

import org.skepsun.kototoro.parsers.MangaLoaderContext
import org.skepsun.kototoro.parsers.MangaSourceParser
import org.skepsun.kototoro.parsers.model.MangaParserSource
import org.skepsun.kototoro.parsers.site.madara.MadaraParser

@MangaSourceParser("MANGASEHRINET", "MangaSehri.net", "tr")
internal class MangaSehriNet(context: MangaLoaderContext) :
	MadaraParser(context, MangaParserSource.MANGASEHRINET, "manga-sehri.net", 20) {
	override val datePattern = "dd MMMM yyyy"
}
