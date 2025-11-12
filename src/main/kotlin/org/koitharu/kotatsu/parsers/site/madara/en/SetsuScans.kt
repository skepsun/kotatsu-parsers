package org.skepsun.kototoro.parsers.site.madara.en

import org.skepsun.kototoro.parsers.MangaLoaderContext
import org.skepsun.kototoro.parsers.MangaSourceParser
import org.skepsun.kototoro.parsers.model.MangaParserSource
import org.skepsun.kototoro.parsers.site.madara.MadaraParser

@MangaSourceParser("SETSUSCANS", "SetsuScans", "en")
internal class SetsuScans(context: MangaLoaderContext) :
	MadaraParser(context, MangaParserSource.SETSUSCANS, "setsuscans.com")
