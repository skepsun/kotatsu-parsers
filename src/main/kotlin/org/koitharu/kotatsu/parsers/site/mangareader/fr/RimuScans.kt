package org.skepsun.kototoro.parsers.site.mangareader.fr

import org.skepsun.kototoro.parsers.MangaLoaderContext
import org.skepsun.kototoro.parsers.MangaSourceParser
import org.skepsun.kototoro.parsers.model.MangaParserSource
import org.skepsun.kototoro.parsers.site.mangareader.MangaReaderParser

@MangaSourceParser("RIMUSCANS", "RimuScans", "fr")
internal class RimuScans(context: MangaLoaderContext) :
	MangaReaderParser(context, MangaParserSource.RIMUSCANS, "rimuscans.com", pageSize = 30, searchPageSize = 10)
