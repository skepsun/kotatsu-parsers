package org.skepsun.kototoro.parsers.site.madara.tr

import org.skepsun.kototoro.parsers.MangaLoaderContext
import org.skepsun.kototoro.parsers.MangaSourceParser
import org.skepsun.kototoro.parsers.model.MangaParserSource
import org.skepsun.kototoro.parsers.site.madara.MadaraParser

@MangaSourceParser("WEBTOONTR", "WebtoonTr", "tr")
internal class Webtoontr(context: MangaLoaderContext) :
	MadaraParser(context, MangaParserSource.WEBTOONTR, "webtoontr.net", 16) {
	override val tagPrefix = "webtoon-kategori/"
	override val listUrl = "webtoon/"
	override val datePattern = "dd/MM/yyyy"
}
