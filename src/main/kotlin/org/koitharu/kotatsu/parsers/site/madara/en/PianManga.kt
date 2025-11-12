package org.skepsun.kototoro.parsers.site.madara.en

import org.skepsun.kototoro.parsers.Broken
import org.skepsun.kototoro.parsers.MangaLoaderContext
import org.skepsun.kototoro.parsers.MangaSourceParser
import org.skepsun.kototoro.parsers.model.MangaParserSource
import org.skepsun.kototoro.parsers.site.madara.MadaraParser

@Broken
@MangaSourceParser("PIANMANGA", "PianManga", "en")
internal class PianManga(context: MangaLoaderContext) :
	MadaraParser(context, MangaParserSource.PIANMANGA, "pianmanga.me", pageSize = 10) {
	override val datePattern = "MMMM dd, yyyy"
}
