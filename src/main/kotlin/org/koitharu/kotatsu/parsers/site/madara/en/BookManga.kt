package org.skepsun.kototoro.parsers.site.madara.en

import org.skepsun.kototoro.parsers.MangaLoaderContext
import org.skepsun.kototoro.parsers.MangaSourceParser
import org.skepsun.kototoro.parsers.model.MangaParserSource
import org.skepsun.kototoro.parsers.site.madara.MadaraParser

@MangaSourceParser("BOOKMANGA", "BookManga", "en")
internal class BookManga(context: MangaLoaderContext) :
	MadaraParser(context, MangaParserSource.BOOKMANGA, "bookmanga.com", 20)
