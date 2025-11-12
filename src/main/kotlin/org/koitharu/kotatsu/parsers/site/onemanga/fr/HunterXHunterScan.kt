package org.skepsun.kototoro.parsers.site.onemanga.fr

import org.skepsun.kototoro.parsers.MangaLoaderContext
import org.skepsun.kototoro.parsers.MangaSourceParser
import org.skepsun.kototoro.parsers.model.MangaParserSource
import org.skepsun.kototoro.parsers.site.onemanga.OneMangaParser

@MangaSourceParser("HUNTERXHUNTERSCAN", "HunterXHunterScan", "fr")
internal class HunterXHunterScan(context: MangaLoaderContext) :
	OneMangaParser(context, MangaParserSource.HUNTERXHUNTERSCAN, "hunterxhunterscan.com")
