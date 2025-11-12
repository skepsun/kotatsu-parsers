package org.skepsun.kototoro.parsers.site.madara.en

import org.skepsun.kototoro.parsers.MangaLoaderContext
import org.skepsun.kototoro.parsers.MangaSourceParser
import org.skepsun.kototoro.parsers.model.MangaParserSource
import org.skepsun.kototoro.parsers.site.madara.MadaraParser

@MangaSourceParser("LIKEMANGAIN", "LikeManga.in", "en")
internal class LikeMangaIn(context: MangaLoaderContext) :
	MadaraParser(context, MangaParserSource.LIKEMANGAIN, "likemanga.in", 36) {
	override val datePattern = "d MMMM, yyyy"
}
