package org.skepsun.kototoro.parsers.site.mangareader.es

import org.skepsun.kototoro.parsers.MangaLoaderContext
import org.skepsun.kototoro.parsers.MangaSourceParser
import org.skepsun.kototoro.parsers.model.MangaParserSource
import org.skepsun.kototoro.parsers.site.mangareader.MangaReaderParser

@MangaSourceParser("ASIALOTUSS", "AsiaLotuss", "es")
internal class AsiaLotuss(context: MangaLoaderContext) :
	MangaReaderParser(context, MangaParserSource.ASIALOTUSS, "asialotuss.com", pageSize = 20, searchPageSize = 10)
