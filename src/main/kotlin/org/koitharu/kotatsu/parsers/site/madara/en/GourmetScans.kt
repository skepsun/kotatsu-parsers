package org.skepsun.kototoro.parsers.site.madara.en

import org.skepsun.kototoro.parsers.MangaLoaderContext
import org.skepsun.kototoro.parsers.MangaSourceParser
import org.skepsun.kototoro.parsers.model.MangaParserSource
import org.skepsun.kototoro.parsers.site.madara.MadaraParser

@MangaSourceParser("GOURMETSCANS", "GourmetScans", "en")
internal class GourmetScans(context: MangaLoaderContext) :
	MadaraParser(context, MangaParserSource.GOURMETSCANS, "gourmetsupremacy.com") {
	override val listUrl = "project/"
	override val tagPrefix = "genre/"
	override val stylePage = ""
}
