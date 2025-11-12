package org.skepsun.kototoro.parsers.site.onemanga.fr

import org.skepsun.kototoro.parsers.MangaLoaderContext
import org.skepsun.kototoro.parsers.MangaSourceParser
import org.skepsun.kototoro.parsers.model.MangaParserSource
import org.skepsun.kototoro.parsers.site.onemanga.OneMangaParser

@MangaSourceParser("MASHLESCAN", "MashleScan", "fr")
internal class MashleScan(context: MangaLoaderContext) :
	OneMangaParser(context, MangaParserSource.MASHLESCAN, "mashlescan.fr")
