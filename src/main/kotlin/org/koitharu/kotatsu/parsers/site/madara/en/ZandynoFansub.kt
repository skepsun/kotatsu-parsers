package org.skepsun.kototoro.parsers.site.madara.en

import org.skepsun.kototoro.parsers.MangaLoaderContext
import org.skepsun.kototoro.parsers.MangaSourceParser
import org.skepsun.kototoro.parsers.model.MangaParserSource
import org.skepsun.kototoro.parsers.site.madara.MadaraParser

@MangaSourceParser("ZANDYNOFANSUB", "Zandyno Fansub", "en")
internal class ZandynoFansub(context: MangaLoaderContext) :
	MadaraParser(context, MangaParserSource.ZANDYNOFANSUB, "zandynofansub.aishiteru.org", 20) {
	override val listUrl = "series/"
	override val datePattern = "dd.MM.yyyy"
}
