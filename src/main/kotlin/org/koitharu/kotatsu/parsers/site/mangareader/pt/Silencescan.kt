package org.skepsun.kototoro.parsers.site.mangareader.pt

import org.skepsun.kototoro.parsers.MangaLoaderContext
import org.skepsun.kototoro.parsers.MangaSourceParser
import org.skepsun.kototoro.parsers.model.ContentType
import org.skepsun.kototoro.parsers.model.MangaParserSource
import org.skepsun.kototoro.parsers.site.mangareader.MangaReaderParser

@MangaSourceParser("SILENCESCAN", "SilenceScan", "pt", ContentType.HENTAI)
internal class Silencescan(context: MangaLoaderContext) :
	MangaReaderParser(
		context,
		MangaParserSource.SILENCESCAN,
		"silencescan.com.br",
		pageSize = 35,
		searchPageSize = 35,
	) {
	override val datePattern = "MMM d, yyyy"
}
