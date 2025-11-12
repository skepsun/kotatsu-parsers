package org.skepsun.kototoro.parsers.site.madara.tr

import org.skepsun.kototoro.parsers.MangaLoaderContext
import org.skepsun.kototoro.parsers.MangaSourceParser
import org.skepsun.kototoro.parsers.model.MangaParserSource
import org.skepsun.kototoro.parsers.site.madara.MadaraParser


@MangaSourceParser("MUGIMANGA", "MugiManga", "tr")
internal class MugiManga(context: MangaLoaderContext) :
	MadaraParser(context, MangaParserSource.MUGIMANGA, "mugimanga.com", 20) {
	override val datePattern = "dd/MM/yyyy"
}
