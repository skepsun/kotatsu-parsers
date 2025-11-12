package org.skepsun.kototoro.parsers.site.onemanga.fr

import org.skepsun.kototoro.parsers.MangaLoaderContext
import org.skepsun.kototoro.parsers.MangaSourceParser
import org.skepsun.kototoro.parsers.model.MangaParserSource
import org.skepsun.kototoro.parsers.site.onemanga.OneMangaParser

@MangaSourceParser("BLUELOCKSCAN", "BlueLockScan", "fr")
internal class BlueLockScan(context: MangaLoaderContext) :
	OneMangaParser(context, MangaParserSource.BLUELOCKSCAN, "bluelockscan.com")
