package org.skepsun.kototoro.parsers.site.madara.en

import org.skepsun.kototoro.parsers.MangaLoaderContext
import org.skepsun.kototoro.parsers.MangaSourceParser
import org.skepsun.kototoro.parsers.model.MangaParserSource
import org.skepsun.kototoro.parsers.site.madara.MadaraParser

@MangaSourceParser("MANGAFREAK", "MangaFreak", "en")
internal class MangaFreak(context: MangaLoaderContext) :
	MadaraParser(context, MangaParserSource.MANGAFREAK, "mangafreak.online") {
	override val postReq = true
	override val datePattern = "dd MMMM، yyyy"
}
