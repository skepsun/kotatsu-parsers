package org.skepsun.kototoro.parsers.site.madara.en

import org.skepsun.kototoro.parsers.MangaLoaderContext
import org.skepsun.kototoro.parsers.MangaSourceParser
import org.skepsun.kototoro.parsers.model.ContentType
import org.skepsun.kototoro.parsers.model.MangaParserSource
import org.skepsun.kototoro.parsers.site.madara.MadaraParser

@MangaSourceParser("MANGA18X", "Manga18x", "en", ContentType.HENTAI)
internal class Manga18x(context: MangaLoaderContext) :
	MadaraParser(context, MangaParserSource.MANGA18X, "manga18x.net", 24)
