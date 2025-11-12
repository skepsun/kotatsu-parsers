package org.skepsun.kototoro.parsers.site.madara.en

import org.skepsun.kototoro.parsers.MangaLoaderContext
import org.skepsun.kototoro.parsers.MangaSourceParser
import org.skepsun.kototoro.parsers.model.ContentType
import org.skepsun.kototoro.parsers.model.MangaParserSource
import org.skepsun.kototoro.parsers.site.madara.MadaraParser

@MangaSourceParser("TOONGOD", "ToonGod", "en", ContentType.HENTAI)
internal class ToonGod(context: MangaLoaderContext) :
	MadaraParser(context, MangaParserSource.TOONGOD, "www.toongod.org", 18) {
	override val listUrl = "webtoon/"
	override val tagPrefix = "webtoon-genre/"
	override val datePattern = "d MMM yyyy"
	override val withoutAjax = true
}
