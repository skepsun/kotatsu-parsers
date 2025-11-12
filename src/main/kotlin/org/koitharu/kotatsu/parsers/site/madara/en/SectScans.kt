package org.skepsun.kototoro.parsers.site.madara.en

import org.skepsun.kototoro.parsers.MangaLoaderContext
import org.skepsun.kototoro.parsers.MangaSourceParser
import org.skepsun.kototoro.parsers.model.MangaParserSource
import org.skepsun.kototoro.parsers.site.madara.MadaraParser

@MangaSourceParser("SECTSCANS", "SectScans", "en")
internal class SectScans(context: MangaLoaderContext) :
	MadaraParser(context, MangaParserSource.SECTSCANS, "sectscans.com") {
	override val listUrl = "comics/"
}
