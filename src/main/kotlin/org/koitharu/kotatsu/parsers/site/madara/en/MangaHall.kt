package org.skepsun.kototoro.parsers.site.madara.en

import org.skepsun.kototoro.parsers.Broken
import org.skepsun.kototoro.parsers.MangaLoaderContext
import org.skepsun.kototoro.parsers.MangaSourceParser
import org.skepsun.kototoro.parsers.model.ContentType
import org.skepsun.kototoro.parsers.model.MangaParserSource
import org.skepsun.kototoro.parsers.site.madara.MadaraParser

@Broken
@MangaSourceParser("MANGAHALL", "MangaHolic", "en", ContentType.HENTAI)
internal class MangaHall(context: MangaLoaderContext) :
	MadaraParser(context, MangaParserSource.MANGAHALL, "mangaholic.org", 24)
