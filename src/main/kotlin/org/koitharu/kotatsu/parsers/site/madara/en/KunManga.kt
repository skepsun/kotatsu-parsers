package org.skepsun.kototoro.parsers.site.madara.en

import org.skepsun.kototoro.parsers.MangaLoaderContext
import org.skepsun.kototoro.parsers.MangaSourceParser
import org.skepsun.kototoro.parsers.model.MangaParserSource
import org.skepsun.kototoro.parsers.site.madara.MadaraParser

@MangaSourceParser("KUNMANGA", "KunManga", "en")
internal class KunManga(context: MangaLoaderContext) :
	MadaraParser(context, MangaParserSource.KUNMANGA, "kunmanga.com", 10)
