package org.skepsun.kototoro.parsers.site.mangareader.en

import org.skepsun.kototoro.parsers.MangaLoaderContext
import org.skepsun.kototoro.parsers.MangaSourceParser
import org.skepsun.kototoro.parsers.model.MangaParserSource
import org.skepsun.kototoro.parsers.site.mangareader.MangaReaderParser

@MangaSourceParser("FURYMANGA", "KingOfScans", "en")
internal class FuryManga(context: MangaLoaderContext) :
	MangaReaderParser(context, MangaParserSource.FURYMANGA, "myshojo.com", pageSize = 30, searchPageSize = 10) {
	override val listUrl = "/comics"
}
