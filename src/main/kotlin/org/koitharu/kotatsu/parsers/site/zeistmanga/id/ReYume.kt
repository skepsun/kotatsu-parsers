package org.skepsun.kototoro.parsers.site.zeistmanga.id

import org.skepsun.kototoro.parsers.MangaLoaderContext
import org.skepsun.kototoro.parsers.MangaSourceParser
import org.skepsun.kototoro.parsers.model.MangaParserSource
import org.skepsun.kototoro.parsers.site.zeistmanga.ZeistMangaParser

@MangaSourceParser("REYUME", "ReYume", "id")
internal class ReYume(context: MangaLoaderContext) :
	ZeistMangaParser(context, MangaParserSource.REYUME, "www.re-yume.my.id")
