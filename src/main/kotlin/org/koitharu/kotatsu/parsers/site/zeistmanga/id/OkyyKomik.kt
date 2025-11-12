package org.skepsun.kototoro.parsers.site.zeistmanga.id

import org.skepsun.kototoro.parsers.MangaLoaderContext
import org.skepsun.kototoro.parsers.MangaSourceParser
import org.skepsun.kototoro.parsers.model.MangaParserSource
import org.skepsun.kototoro.parsers.site.zeistmanga.ZeistMangaParser

@MangaSourceParser("OKYYKOMIK", "OkyyKomik", "id")
internal class OkyyKomik(context: MangaLoaderContext) :
	ZeistMangaParser(context, MangaParserSource.OKYYKOMIK, "www.okyykomik.my.id")
