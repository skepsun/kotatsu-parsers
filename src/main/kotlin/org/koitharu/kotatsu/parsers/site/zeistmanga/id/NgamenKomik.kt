package org.skepsun.kototoro.parsers.site.zeistmanga.id

import org.skepsun.kototoro.parsers.MangaLoaderContext
import org.skepsun.kototoro.parsers.MangaSourceParser
import org.skepsun.kototoro.parsers.model.MangaParserSource
import org.skepsun.kototoro.parsers.site.zeistmanga.ZeistMangaParser

@MangaSourceParser("NGAMENKOMIK", "NgamenKomik", "id")
internal class NgamenKomik(context: MangaLoaderContext) :
	ZeistMangaParser(context, MangaParserSource.NGAMENKOMIK, "ngamenkomik05.blogspot.com")
