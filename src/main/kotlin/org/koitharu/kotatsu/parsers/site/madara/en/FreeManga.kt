package org.skepsun.kototoro.parsers.site.madara.en

import org.skepsun.kototoro.parsers.MangaLoaderContext
import org.skepsun.kototoro.parsers.MangaSourceParser
import org.skepsun.kototoro.parsers.model.MangaParserSource
import org.skepsun.kototoro.parsers.site.madara.MadaraParser

@MangaSourceParser("FREEMANGA", "FreeManga", "en")
internal class FreeManga(context: MangaLoaderContext) :
	MadaraParser(context, MangaParserSource.FREEMANGA, "freemanga.me") {
	override val datePattern = "MMMM dd, yyyy"
}
