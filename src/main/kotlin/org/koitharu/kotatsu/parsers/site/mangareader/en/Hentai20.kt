package org.skepsun.kototoro.parsers.site.mangareader.en

import org.skepsun.kototoro.parsers.MangaLoaderContext
import org.skepsun.kototoro.parsers.MangaSourceParser
import org.skepsun.kototoro.parsers.model.ContentType
import org.skepsun.kototoro.parsers.model.MangaParserSource
import org.skepsun.kototoro.parsers.site.mangareader.MangaReaderParser

@MangaSourceParser("HENTAI20", "Hentai20", "en", ContentType.HENTAI)
internal class Hentai20(context: MangaLoaderContext) :
	MangaReaderParser(context, MangaParserSource.HENTAI20, "hentai20.io", pageSize = 20, searchPageSize = 10)
