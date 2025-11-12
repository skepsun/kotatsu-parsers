package org.skepsun.kototoro.parsers.site.madara.en

import org.skepsun.kototoro.parsers.MangaLoaderContext
import org.skepsun.kototoro.parsers.MangaSourceParser
import org.skepsun.kototoro.parsers.model.ContentType
import org.skepsun.kototoro.parsers.model.MangaParserSource
import org.skepsun.kototoro.parsers.site.madara.MadaraParser

@MangaSourceParser("PAWMANGA", "PawManga", "en", ContentType.HENTAI)
internal class PawManga(context: MangaLoaderContext) :
	MadaraParser(context, MangaParserSource.PAWMANGA, "pawmanga.com", 10)
