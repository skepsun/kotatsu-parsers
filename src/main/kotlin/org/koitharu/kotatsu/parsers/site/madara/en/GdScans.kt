package org.skepsun.kototoro.parsers.site.madara.en

import org.skepsun.kototoro.parsers.MangaLoaderContext
import org.skepsun.kototoro.parsers.MangaSourceParser
import org.skepsun.kototoro.parsers.model.MangaParserSource
import org.skepsun.kototoro.parsers.site.madara.MadaraParser

@MangaSourceParser("GDSCANS", "GdScans", "en")
internal class GdScans(context: MangaLoaderContext) :
	MadaraParser(context, MangaParserSource.GDSCANS, "gdscans.com", 10) {
	override val tagPrefix = "webtoon-genre/"
}
