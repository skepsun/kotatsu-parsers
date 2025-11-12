package org.skepsun.kototoro.parsers.site.guya.en

import org.skepsun.kototoro.parsers.MangaLoaderContext
import org.skepsun.kototoro.parsers.MangaSourceParser
import org.skepsun.kototoro.parsers.model.MangaParserSource
import org.skepsun.kototoro.parsers.site.guya.GuyaParser

@MangaSourceParser("GUYACUBARI", "GuyaCubari", "en")
internal class GuyaCubari(context: MangaLoaderContext) :
	GuyaParser(context, MangaParserSource.GUYACUBARI, "guya.cubari.moe")
