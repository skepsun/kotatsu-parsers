package org.skepsun.kototoro.parsers.site.madara.en

import org.skepsun.kototoro.parsers.MangaLoaderContext
import org.skepsun.kototoro.parsers.MangaSourceParser
import org.skepsun.kototoro.parsers.model.MangaParserSource
import org.skepsun.kototoro.parsers.site.madara.MadaraParser

@MangaSourceParser("ZINCHANMANGA_NET", "ZinchanManga.net", "en")
internal class ZinchanMangaNet(context: MangaLoaderContext) :
	MadaraParser(context, MangaParserSource.ZINCHANMANGA_NET, "zinchangmanga.net", 10)
