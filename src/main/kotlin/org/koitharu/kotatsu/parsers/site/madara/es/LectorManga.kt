package org.skepsun.kototoro.parsers.site.madara.es

import org.skepsun.kototoro.parsers.MangaLoaderContext
import org.skepsun.kototoro.parsers.MangaSourceParser
import org.skepsun.kototoro.parsers.model.MangaParserSource
import org.skepsun.kototoro.parsers.site.madara.MadaraParser

@MangaSourceParser("LECTORMANGA", "LectorManga", "es")
internal class LectorManga(context: MangaLoaderContext) :
	MadaraParser(context, MangaParserSource.LECTORMANGA, "lectormangaa.com") {
	override val listUrl = "biblioteca/"
	override val tagPrefix = "comics-genero/"
}
