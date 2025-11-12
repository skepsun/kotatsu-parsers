package org.skepsun.kototoro.parsers.site.madara.en

import org.skepsun.kototoro.parsers.MangaLoaderContext
import org.skepsun.kototoro.parsers.MangaSourceParser
import org.skepsun.kototoro.parsers.model.ContentType
import org.skepsun.kototoro.parsers.model.MangaParserSource
import org.skepsun.kototoro.parsers.site.madara.MadaraParser

@MangaSourceParser("YAOIHUB", "YaoiHub", "en", ContentType.HENTAI)
internal class YaoiHub(context: MangaLoaderContext) :
	MadaraParser(context, MangaParserSource.YAOIHUB, "yaoihub.com")
