package org.skepsun.kototoro.parsers.site.mangareader.id

import org.skepsun.kototoro.parsers.MangaLoaderContext
import org.skepsun.kototoro.parsers.MangaSourceParser
import org.skepsun.kototoro.parsers.model.MangaParserSource
import org.skepsun.kototoro.parsers.site.mangareader.MangaReaderParser

@MangaSourceParser("MANHWAKU", "Manhwaku", "id")
internal class Manhwaku(context: MangaLoaderContext) :
	MangaReaderParser(context, MangaParserSource.MANHWAKU, "manhwaku.id", pageSize = 20, searchPageSize = 10)
