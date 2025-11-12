package org.skepsun.kototoro.parsers.site.madara.en

import org.skepsun.kototoro.parsers.MangaLoaderContext
import org.skepsun.kototoro.parsers.MangaSourceParser
import org.skepsun.kototoro.parsers.model.MangaParserSource
import org.skepsun.kototoro.parsers.site.madara.MadaraParser

@MangaSourceParser("MANGA1ST", "Manga1st", "en")
internal class Manga1st(context: MangaLoaderContext) :
	MadaraParser(context, MangaParserSource.MANGA1ST, "manga1st.online") {
	override val datePattern = "d MMMM، yyyy"
}
