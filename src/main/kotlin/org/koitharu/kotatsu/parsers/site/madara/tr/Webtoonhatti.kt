package org.skepsun.kototoro.parsers.site.madara.tr

import org.skepsun.kototoro.parsers.MangaLoaderContext
import org.skepsun.kototoro.parsers.MangaSourceParser
import org.skepsun.kototoro.parsers.model.MangaParserSource
import org.skepsun.kototoro.parsers.site.madara.MadaraParser

@MangaSourceParser("WEBTOONHATTI", "WebtoonHatti", "tr")
internal class Webtoonhatti(context: MangaLoaderContext) :
	MadaraParser(context, MangaParserSource.WEBTOONHATTI, "webtoonhatti.club", 20) {
	override val listUrl = "webtoon/"
	override val tagPrefix = "webtoon-tur/"
	override val datePattern = "d MMMM"
}
