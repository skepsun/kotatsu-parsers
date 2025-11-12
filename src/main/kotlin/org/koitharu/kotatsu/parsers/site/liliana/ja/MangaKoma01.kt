package org.skepsun.kototoro.parsers.site.liliana.ja

import org.skepsun.kototoro.parsers.MangaLoaderContext
import org.skepsun.kototoro.parsers.MangaSourceParser
import org.skepsun.kototoro.parsers.model.MangaParserSource
import org.skepsun.kototoro.parsers.site.liliana.LilianaParser

@MangaSourceParser("MANGAKOMA01", "MangaKoma01", "ja")
internal class MangaKoma01(context: MangaLoaderContext) :
	LilianaParser(context, MangaParserSource.MANGAKOMA01, "mangakoma01.com")
