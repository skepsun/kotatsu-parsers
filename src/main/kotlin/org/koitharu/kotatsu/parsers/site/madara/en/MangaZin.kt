package org.skepsun.kototoro.parsers.site.madara.en

import org.skepsun.kototoro.parsers.MangaLoaderContext
import org.skepsun.kototoro.parsers.MangaSourceParser
import org.skepsun.kototoro.parsers.model.MangaParserSource
import org.skepsun.kototoro.parsers.site.madara.MadaraParser

@MangaSourceParser("MANGAZIN", "MangaZin", "en")
internal class MangaZin(context: MangaLoaderContext) :
	MadaraParser(context, MangaParserSource.MANGAZIN, "mangazin.org")
