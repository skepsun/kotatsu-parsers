package org.skepsun.kototoro.parsers.site.madara.es

import org.skepsun.kototoro.parsers.MangaLoaderContext
import org.skepsun.kototoro.parsers.MangaSourceParser
import org.skepsun.kototoro.parsers.model.ContentType
import org.skepsun.kototoro.parsers.model.MangaParserSource
import org.skepsun.kototoro.parsers.site.madara.MadaraParser

@MangaSourceParser("VERMANHWA", "Vermanhwa", "es", ContentType.HENTAI)
internal class Vermanhwa(context: MangaLoaderContext) :
	MadaraParser(context, MangaParserSource.VERMANHWA, "vermanhwa.com", 10)
