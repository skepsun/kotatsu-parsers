package org.skepsun.kototoro.parsers.site.zeistmanga.pt

import org.skepsun.kototoro.parsers.MangaLoaderContext
import org.skepsun.kototoro.parsers.MangaSourceParser
import org.skepsun.kototoro.parsers.model.MangaParserSource
import org.skepsun.kototoro.parsers.site.zeistmanga.ZeistMangaParser

@MangaSourceParser("WOLFSCANBR", "WolfScanBr", "pt")
internal class WolfScanBr(context: MangaLoaderContext) :
	ZeistMangaParser(context, MangaParserSource.WOLFSCANBR, "wolfscanbr.blogspot.com")
