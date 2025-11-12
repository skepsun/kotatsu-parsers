package org.skepsun.kototoro.parsers.site.zeistmanga.id

import org.skepsun.kototoro.parsers.MangaLoaderContext
import org.skepsun.kototoro.parsers.MangaSourceParser
import org.skepsun.kototoro.parsers.model.MangaParserSource
import org.skepsun.kototoro.parsers.site.zeistmanga.ZeistMangaParser


@MangaSourceParser("MAGERIN", "Magerin", "id")
internal class Magerin(context: MangaLoaderContext) :
	ZeistMangaParser(context, MangaParserSource.MAGERIN, "www.magerin.com")
