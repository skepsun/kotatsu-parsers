package org.skepsun.kototoro.parsers.site.madara.tr

import org.skepsun.kototoro.parsers.MangaLoaderContext
import org.skepsun.kototoro.parsers.MangaSourceParser
import org.skepsun.kototoro.parsers.model.MangaParserSource
import org.skepsun.kototoro.parsers.site.madara.MadaraParser

@MangaSourceParser("MANGAGEZGINI", "MangaGezgini", "tr")
internal class MangaGezgini(context: MangaLoaderContext) :
	MadaraParser(context, MangaParserSource.MANGAGEZGINI, "mangagezginim.com", pageSize = 20) {
	override val datePattern = "dd/MM/yyyy"
}
