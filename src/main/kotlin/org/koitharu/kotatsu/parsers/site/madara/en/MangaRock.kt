package org.skepsun.kototoro.parsers.site.madara.en

import org.skepsun.kototoro.parsers.MangaLoaderContext
import org.skepsun.kototoro.parsers.MangaSourceParser
import org.skepsun.kototoro.parsers.model.MangaParserSource
import org.skepsun.kototoro.parsers.site.madara.MadaraParser

@MangaSourceParser("MANGAROCK", "MangaRock", "en")
internal class MangaRock(context: MangaLoaderContext) :
	MadaraParser(context, MangaParserSource.MANGAROCK, "mangarockteam.com") {
	override val datePattern = "MMMM dd, yyyy"
}
