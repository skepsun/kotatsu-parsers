package org.skepsun.kototoro.parsers.site.foolslide.es

import org.skepsun.kototoro.parsers.MangaLoaderContext
import org.skepsun.kototoro.parsers.MangaSourceParser
import org.skepsun.kototoro.parsers.model.MangaParserSource
import org.skepsun.kototoro.parsers.site.foolslide.FoolSlideParser

@MangaSourceParser("MENUDO_FANSUB", "Menudo Fansub", "es")
internal class MenudoFansub(context: MangaLoaderContext) :
	FoolSlideParser(context, MangaParserSource.MENUDO_FANSUB, "www.menudo-fansub.com", 25) {
	override val searchUrl = "slide/search/"
	override val listUrl = "slide/directory/"
}
