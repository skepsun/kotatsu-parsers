package org.skepsun.kototoro.parsers.site.madara.tr

import org.skepsun.kototoro.parsers.MangaLoaderContext
import org.skepsun.kototoro.parsers.MangaSourceParser
import org.skepsun.kototoro.parsers.model.MangaParserSource
import org.skepsun.kototoro.parsers.site.madara.MadaraParser

@MangaSourceParser("ARAZNOVEL", "ArazNovel", "tr")
internal class ArazNovel(context: MangaLoaderContext) :
	MadaraParser(context, MangaParserSource.ARAZNOVEL, "araznovel.com", 10) {
	override val datePattern = "d MMMM yyyy"
	override val postReq = true
}
