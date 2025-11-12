package org.skepsun.kototoro.parsers.site.madara.en

import org.skepsun.kototoro.parsers.Broken
import org.skepsun.kototoro.parsers.MangaLoaderContext
import org.skepsun.kototoro.parsers.MangaSourceParser
import org.skepsun.kototoro.parsers.model.MangaParserSource
import org.skepsun.kototoro.parsers.site.madara.MadaraParser

@Broken
@MangaSourceParser("MANGARUBY", "MangaRuby", "en")
internal class MangaRuby(context: MangaLoaderContext) :
	MadaraParser(context, MangaParserSource.MANGARUBY, "mangaruby.com", 10)
