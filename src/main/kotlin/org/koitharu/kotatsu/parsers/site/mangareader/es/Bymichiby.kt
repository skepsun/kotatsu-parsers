package org.skepsun.kototoro.parsers.site.mangareader.es

import org.skepsun.kototoro.parsers.MangaLoaderContext
import org.skepsun.kototoro.parsers.MangaSourceParser
import org.skepsun.kototoro.parsers.model.ContentType
import org.skepsun.kototoro.parsers.model.MangaParserSource
import org.skepsun.kototoro.parsers.site.mangareader.MangaReaderParser

@MangaSourceParser("BYMICHIBY", "Bymichiby", "es", ContentType.HENTAI)
internal class Bymichiby(context: MangaLoaderContext) :
	MangaReaderParser(context, MangaParserSource.BYMICHIBY, "bymichiby.com", pageSize = 20, searchPageSize = 10)
