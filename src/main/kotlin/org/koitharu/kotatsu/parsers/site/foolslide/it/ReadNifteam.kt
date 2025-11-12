package org.skepsun.kototoro.parsers.site.foolslide.it

import org.skepsun.kototoro.parsers.MangaLoaderContext
import org.skepsun.kototoro.parsers.MangaSourceParser
import org.skepsun.kototoro.parsers.model.MangaParserSource
import org.skepsun.kototoro.parsers.site.foolslide.FoolSlideParser

@MangaSourceParser("READNIFTEAM", "ReadNifTeam", "it")
internal class ReadNifteam(context: MangaLoaderContext) :
	FoolSlideParser(context, MangaParserSource.READNIFTEAM, "read-nifteam.info") {
	override val searchUrl = "slide/search/"
	override val listUrl = "slide/directory/"
}
