package org.skepsun.kototoro.parsers.site.madara.en

import org.skepsun.kototoro.parsers.MangaLoaderContext
import org.skepsun.kototoro.parsers.MangaSourceParser
import org.skepsun.kototoro.parsers.model.MangaParserSource
import org.skepsun.kototoro.parsers.site.madara.MadaraParser

@MangaSourceParser("BANANA_MANGA", "BananaManga", "en")
internal class BananaManga(context: MangaLoaderContext) :
	MadaraParser(context, MangaParserSource.BANANA_MANGA, "bananamanga.net", 20)
