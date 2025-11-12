package org.skepsun.kototoro.parsers.site.madara.en

import org.skepsun.kototoro.parsers.MangaLoaderContext
import org.skepsun.kototoro.parsers.MangaSourceParser
import org.skepsun.kototoro.parsers.model.MangaParserSource
import org.skepsun.kototoro.parsers.site.madara.MadaraParser

@MangaSourceParser("MANGAOWL_IO", "MangaOwl.io", "en")
internal class MangaOwlIo(context: MangaLoaderContext) :
	MadaraParser(context, MangaParserSource.MANGAOWL_IO, "mangaowl.io") {
	override val listUrl = "mangaowl-all/"
}
