package org.skepsun.kototoro.parsers.site.madara.es

import org.skepsun.kototoro.parsers.MangaLoaderContext
import org.skepsun.kototoro.parsers.MangaSourceParser
import org.skepsun.kototoro.parsers.model.MangaParserSource
import org.skepsun.kototoro.parsers.site.madara.MadaraParser

@MangaSourceParser("APOLL_COMICS", "ApollComics", "es")
internal class ApollComics(context: MangaLoaderContext) :
	MadaraParser(context, MangaParserSource.APOLL_COMICS, "apollcomics.es", 10)
