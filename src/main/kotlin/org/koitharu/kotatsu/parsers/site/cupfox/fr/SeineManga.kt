package org.skepsun.kototoro.parsers.site.cupfox.fr

import org.skepsun.kototoro.parsers.MangaLoaderContext
import org.skepsun.kototoro.parsers.MangaSourceParser
import org.skepsun.kototoro.parsers.model.MangaParserSource
import org.skepsun.kototoro.parsers.site.cupfox.CupFoxParser

@MangaSourceParser("SEINEMANGA", "SeineManga", "fr")
internal class SeineManga(context: MangaLoaderContext) :
	CupFoxParser(context, MangaParserSource.SEINEMANGA, "www.seinemanga.com")
