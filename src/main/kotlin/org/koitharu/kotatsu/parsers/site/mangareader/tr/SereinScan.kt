package org.skepsun.kototoro.parsers.site.mangareader.tr

import org.skepsun.kototoro.parsers.MangaLoaderContext
import org.skepsun.kototoro.parsers.MangaSourceParser
import org.skepsun.kototoro.parsers.model.MangaParserSource
import org.skepsun.kototoro.parsers.site.mangareader.MangaReaderParser

@MangaSourceParser("SEREINSCAN", "SereinScan", "tr")
internal class SereinScan(context: MangaLoaderContext) :
	MangaReaderParser(context, MangaParserSource.SEREINSCAN, "sereinscan.com", pageSize = 20, searchPageSize = 10)
