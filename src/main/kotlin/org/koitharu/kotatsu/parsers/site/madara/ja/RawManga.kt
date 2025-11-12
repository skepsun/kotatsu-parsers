package org.skepsun.kototoro.parsers.site.madara.ja

import org.skepsun.kototoro.parsers.Broken
import org.skepsun.kototoro.parsers.MangaLoaderContext
import org.skepsun.kototoro.parsers.MangaSourceParser
import org.skepsun.kototoro.parsers.model.MangaParserSource
import org.skepsun.kototoro.parsers.site.madara.MadaraParser

@Broken
@MangaSourceParser("RAWMANGA", "RawManga", "ja")
internal class RawManga(context: MangaLoaderContext) :
	MadaraParser(context, MangaParserSource.RAWMANGA, "rawmanga.su", 24) {
	override val listUrl = "r/"
	override val selectPage = "div.mg-item"
}
