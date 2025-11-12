package org.skepsun.kototoro.parsers.site.mangareader.es

import org.skepsun.kototoro.parsers.MangaLoaderContext
import org.skepsun.kototoro.parsers.MangaSourceParser
import org.skepsun.kototoro.parsers.model.MangaParserSource
import org.skepsun.kototoro.parsers.site.mangareader.MangaReaderParser

@MangaSourceParser("RAIKISCAN", "RaikiScan", "es")
internal class Raikiscan(context: MangaLoaderContext) :
	MangaReaderParser(context, MangaParserSource.RAIKISCAN, "raikiscan.com", pageSize = 20, searchPageSize = 20) {
	override val datePattern = "MMM d, yyyy"
}
