package org.skepsun.kototoro.parsers.site.sinmh.zh

import org.skepsun.kototoro.parsers.MangaLoaderContext
import org.skepsun.kototoro.parsers.MangaSourceParser
import org.skepsun.kototoro.parsers.model.MangaParserSource
import org.skepsun.kototoro.parsers.site.sinmh.SinmhParser

@MangaSourceParser("YKMH", "Ykmh", "zh")
internal class Ykmh(context: MangaLoaderContext) :
	SinmhParser(context, MangaParserSource.YKMH, "www.ykmh.net")
