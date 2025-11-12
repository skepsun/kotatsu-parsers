package org.skepsun.kototoro.parsers.site.madara.ru

import org.skepsun.kototoro.parsers.Broken
import org.skepsun.kototoro.parsers.MangaLoaderContext
import org.skepsun.kototoro.parsers.MangaSourceParser
import org.skepsun.kototoro.parsers.model.MangaParserSource
import org.skepsun.kototoro.parsers.site.madara.MadaraParser

@Broken
@MangaSourceParser("BEST_MANGA", "BestManga", "ru")
internal class BestManga(context: MangaLoaderContext) :
	MadaraParser(context, MangaParserSource.BEST_MANGA, "bestmanga.club") {
	override val datePattern = "dd.MM.yyyy"
	override val postReq = true
}
