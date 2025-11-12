package org.skepsun.kototoro.parsers.site.onemanga.fr

import org.skepsun.kototoro.parsers.MangaLoaderContext
import org.skepsun.kototoro.parsers.MangaSourceParser
import org.skepsun.kototoro.parsers.model.MangaParserSource
import org.skepsun.kototoro.parsers.site.onemanga.OneMangaParser

@MangaSourceParser("KINGDOMSCAN", "KingdomScan", "fr")
internal class KingdomScan(context: MangaLoaderContext) :
	OneMangaParser(context, MangaParserSource.KINGDOMSCAN, "kingdomscan.com")
