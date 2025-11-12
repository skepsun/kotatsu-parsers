package org.skepsun.kototoro.parsers.site.madara.ar

import org.skepsun.kototoro.parsers.MangaLoaderContext
import org.skepsun.kototoro.parsers.MangaSourceParser
import org.skepsun.kototoro.parsers.model.MangaParserSource
import org.skepsun.kototoro.parsers.site.madara.MadaraParser

@MangaSourceParser("GMANGA", "Gmanga", "ar")
internal class Gmanga(context: MangaLoaderContext) :
	MadaraParser(context, MangaParserSource.GMANGA, "gmanga.site")
