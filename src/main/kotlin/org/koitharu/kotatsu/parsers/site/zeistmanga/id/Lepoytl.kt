package org.skepsun.kototoro.parsers.site.zeistmanga.id

import org.skepsun.kototoro.parsers.MangaLoaderContext
import org.skepsun.kototoro.parsers.MangaSourceParser
import org.skepsun.kototoro.parsers.model.MangaParserSource
import org.skepsun.kototoro.parsers.site.zeistmanga.ZeistMangaParser

@MangaSourceParser("LEPOYTL", "Lepoytl", "id")
internal class Lepoytl(context: MangaLoaderContext) :
	ZeistMangaParser(context, MangaParserSource.LEPOYTL, "www.lepoytl.cloud")
