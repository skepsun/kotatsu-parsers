package org.skepsun.kototoro.parsers.site.mangareader.pt

import org.skepsun.kototoro.parsers.MangaLoaderContext
import org.skepsun.kototoro.parsers.MangaSourceParser
import org.skepsun.kototoro.parsers.model.MangaParserSource
import org.skepsun.kototoro.parsers.site.mangareader.MangaReaderParser

@MangaSourceParser("ORIGAMIORPHEANS", "Origami Orpheans", "pt")
internal class Origamiorpheans(context: MangaLoaderContext) :
	MangaReaderParser(
		context,
		MangaParserSource.ORIGAMIORPHEANS,
		"origami-orpheans.com",
		pageSize = 20,
		searchPageSize = 10,
	)
