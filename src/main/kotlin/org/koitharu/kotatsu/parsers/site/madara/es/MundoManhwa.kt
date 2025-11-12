package org.skepsun.kototoro.parsers.site.madara.es

import org.skepsun.kototoro.parsers.MangaLoaderContext
import org.skepsun.kototoro.parsers.MangaSourceParser
import org.skepsun.kototoro.parsers.model.MangaParserSource
import org.skepsun.kototoro.parsers.site.madara.MadaraParser

@MangaSourceParser("MUNDO_MANHWA", "MundoManhwa", "es")
internal class MundoManhwa(context: MangaLoaderContext) :
	MadaraParser(context, MangaParserSource.MUNDO_MANHWA, "mundomanhwa.com", 10)
