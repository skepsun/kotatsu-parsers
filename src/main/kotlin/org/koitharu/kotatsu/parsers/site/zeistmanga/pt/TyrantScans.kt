package org.skepsun.kototoro.parsers.site.zeistmanga.pt

import org.skepsun.kototoro.parsers.MangaLoaderContext
import org.skepsun.kototoro.parsers.MangaSourceParser
import org.skepsun.kototoro.parsers.model.MangaParserSource
import org.skepsun.kototoro.parsers.site.zeistmanga.ZeistMangaParser

@MangaSourceParser("TYRANTSCANS", "TyrantScans", "pt")
internal class TyrantScans(context: MangaLoaderContext) :
	ZeistMangaParser(context, MangaParserSource.TYRANTSCANS, "www.tyrantscans.com")
