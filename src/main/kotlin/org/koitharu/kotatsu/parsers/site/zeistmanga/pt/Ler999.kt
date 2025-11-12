package org.skepsun.kototoro.parsers.site.zeistmanga.pt

import org.skepsun.kototoro.parsers.MangaLoaderContext
import org.skepsun.kototoro.parsers.MangaSourceParser
import org.skepsun.kototoro.parsers.model.MangaParserSource
import org.skepsun.kototoro.parsers.site.zeistmanga.ZeistMangaParser

@MangaSourceParser("LER999", "Ler999", "pt")
internal class Ler999(context: MangaLoaderContext) :
	ZeistMangaParser(context, MangaParserSource.LER999, "ler999.blogspot.com")
