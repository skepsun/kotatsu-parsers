package org.skepsun.kototoro.parsers.site.madara.en

import org.skepsun.kototoro.parsers.MangaLoaderContext
import org.skepsun.kototoro.parsers.MangaSourceParser
import org.skepsun.kototoro.parsers.model.MangaParserSource
import org.skepsun.kototoro.parsers.site.madara.MadaraParser

@MangaSourceParser("HARIMANGA", "HariManga", "en")
internal class HariManga(context: MangaLoaderContext) :
	MadaraParser(context, MangaParserSource.HARIMANGA, "harimanga.me", pageSize = 10)
