package org.skepsun.kototoro.parsers.site.mangareader.pt

import org.skepsun.kototoro.parsers.MangaLoaderContext
import org.skepsun.kototoro.parsers.MangaSourceParser
import org.skepsun.kototoro.parsers.model.MangaParserSource
import org.skepsun.kototoro.parsers.site.mangareader.MangaReaderParser

@MangaSourceParser("IRISSCANLATOR", "IrisScanlator", "pt")
internal class IrisScanlator(context: MangaLoaderContext) :
	MangaReaderParser(
		context,
		MangaParserSource.IRISSCANLATOR,
		"irisscanlator.com.br",
		pageSize = 20,
		searchPageSize = 10,
	)
