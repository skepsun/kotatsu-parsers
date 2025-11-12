package org.skepsun.kototoro.parsers.site.madara.en

import org.skepsun.kototoro.parsers.MangaLoaderContext
import org.skepsun.kototoro.parsers.MangaSourceParser
import org.skepsun.kototoro.parsers.model.MangaParserSource
import org.skepsun.kototoro.parsers.site.madara.MadaraParser

@MangaSourceParser("COFFEE_MANGA", "CoffeeManga", "en")
internal class CoffeeManga(context: MangaLoaderContext) :
	MadaraParser(context, MangaParserSource.COFFEE_MANGA, "coffeemanga.io")
