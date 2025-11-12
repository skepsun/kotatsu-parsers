package org.skepsun.kototoro.parsers.site.madara.en

import org.skepsun.kototoro.parsers.MangaLoaderContext
import org.skepsun.kototoro.parsers.MangaSourceParser
import org.skepsun.kototoro.parsers.model.ContentType
import org.skepsun.kototoro.parsers.model.MangaParserSource
import org.skepsun.kototoro.parsers.site.madara.MadaraParser

@MangaSourceParser("ZINCHANMANGA", "ZinChanManga.Com", "en", ContentType.HENTAI)
internal class ZinChanManga(context: MangaLoaderContext) :
	MadaraParser(context, MangaParserSource.ZINCHANMANGA, "zinchangmanga.net", 10)
