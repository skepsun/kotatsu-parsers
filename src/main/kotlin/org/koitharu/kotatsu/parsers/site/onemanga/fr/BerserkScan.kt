package org.skepsun.kototoro.parsers.site.onemanga.fr

import org.skepsun.kototoro.parsers.MangaLoaderContext
import org.skepsun.kototoro.parsers.MangaSourceParser
import org.skepsun.kototoro.parsers.model.MangaParserSource
import org.skepsun.kototoro.parsers.site.onemanga.OneMangaParser

@MangaSourceParser("BERSERKSCAN", "BerserkScan", "fr")
internal class BerserkScan(context: MangaLoaderContext) :
	OneMangaParser(context, MangaParserSource.BERSERKSCAN, "berserkscan.com")
