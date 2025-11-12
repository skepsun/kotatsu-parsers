package org.skepsun.kototoro.parsers.site.liliana.en

import org.skepsun.kototoro.parsers.MangaLoaderContext
import org.skepsun.kototoro.parsers.MangaSourceParser
import org.skepsun.kototoro.parsers.model.MangaParserSource
import org.skepsun.kototoro.parsers.site.liliana.LilianaParser

@MangaSourceParser("MANGASECT", "MangaSect", "en")
internal class MangaSect(context: MangaLoaderContext) :
	LilianaParser(context, MangaParserSource.MANGASECT, "mangasect.net")
