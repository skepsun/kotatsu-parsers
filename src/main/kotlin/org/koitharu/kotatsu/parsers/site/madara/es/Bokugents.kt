package org.skepsun.kototoro.parsers.site.madara.es

import org.skepsun.kototoro.parsers.MangaLoaderContext
import org.skepsun.kototoro.parsers.MangaSourceParser
import org.skepsun.kototoro.parsers.model.MangaParserSource
import org.skepsun.kototoro.parsers.site.madara.MadaraParser

@MangaSourceParser("BOKUGENTS", "Bokugents", "es")
internal class Bokugents(context: MangaLoaderContext) :
	MadaraParser(context, MangaParserSource.BOKUGENTS, "bokugents.com")
// For this source need to enable the option to ignore SSL errors
