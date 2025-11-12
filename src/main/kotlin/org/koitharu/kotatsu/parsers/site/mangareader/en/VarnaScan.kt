package org.skepsun.kototoro.parsers.site.mangareader.en

import org.skepsun.kototoro.parsers.MangaLoaderContext
import org.skepsun.kototoro.parsers.MangaSourceParser
import org.skepsun.kototoro.parsers.model.MangaParserSource
import org.skepsun.kototoro.parsers.site.mangareader.MangaReaderParser

@MangaSourceParser("VARNASCAN", "VarnaScan", "en")
internal class VarnaScan(context: MangaLoaderContext) :
	MangaReaderParser(context, MangaParserSource.VARNASCAN, "varnascan.xyz", pageSize = 20, searchPageSize = 10)
