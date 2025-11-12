package org.skepsun.kototoro.parsers.site.foolslide.it

import org.skepsun.kototoro.parsers.MangaLoaderContext
import org.skepsun.kototoro.parsers.MangaSourceParser
import org.skepsun.kototoro.parsers.model.MangaParserSource
import org.skepsun.kototoro.parsers.site.foolslide.FoolSlideParser

@MangaSourceParser("RAMAREADER", "RamaReader", "it")
internal class Ramareader(context: MangaLoaderContext) :
	FoolSlideParser(context, MangaParserSource.RAMAREADER, "www.ramareader.it") {
	override val searchUrl = "read/search/"
	override val listUrl = "read/directory/"
}
