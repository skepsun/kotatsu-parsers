package org.skepsun.kototoro.parsers.site.madara.pl

import org.skepsun.kototoro.parsers.MangaLoaderContext
import org.skepsun.kototoro.parsers.MangaSourceParser
import org.skepsun.kototoro.parsers.model.MangaParserSource
import org.skepsun.kototoro.parsers.site.madara.MadaraParser

@MangaSourceParser("MANGAHONA", "MangaHona", "pl")
internal class MangaHona(context: MangaLoaderContext) :
	MadaraParser(context, MangaParserSource.MANGAHONA, "mangahona.pl") {
	override val datePattern = "yyyy-MM-dd"
}
