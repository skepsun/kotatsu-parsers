package org.skepsun.kototoro.parsers.site.nepnep.en

import org.skepsun.kototoro.parsers.Broken
import org.skepsun.kototoro.parsers.MangaLoaderContext
import org.skepsun.kototoro.parsers.MangaSourceParser
import org.skepsun.kototoro.parsers.model.MangaParserSource
import org.skepsun.kototoro.parsers.site.nepnep.NepnepParser

// site closed in favour of weeb central
@Broken
@MangaSourceParser("MANGA4LIFE", "Manga4Life", "en")
internal class Manga4Life(context: MangaLoaderContext) :
	NepnepParser(context, MangaParserSource.MANGA4LIFE, "manga4life.com")
