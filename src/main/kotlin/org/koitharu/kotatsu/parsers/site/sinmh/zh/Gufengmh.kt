package org.skepsun.kototoro.parsers.site.sinmh.zh

import org.skepsun.kototoro.parsers.MangaLoaderContext
import org.skepsun.kototoro.parsers.MangaSourceParser
import org.skepsun.kototoro.parsers.model.MangaParserSource
import org.skepsun.kototoro.parsers.site.sinmh.SinmhParser
import org.skepsun.kototoro.parsers.Broken

@Broken
@MangaSourceParser("GUFENGMH", "Gufengmh", "zh")
internal class Gufengmh(context: MangaLoaderContext) :
	SinmhParser(context, MangaParserSource.GUFENGMH, "www.gufengmh.com")
