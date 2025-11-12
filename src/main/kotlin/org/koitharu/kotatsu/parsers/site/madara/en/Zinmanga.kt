package org.skepsun.kototoro.parsers.site.madara.en

import org.skepsun.kototoro.parsers.MangaLoaderContext
import org.skepsun.kototoro.parsers.MangaSourceParser
import org.skepsun.kototoro.parsers.model.MangaParserSource
import org.skepsun.kototoro.parsers.site.madara.MadaraParser

@MangaSourceParser("ZINMANGA", "ZinManga.net", "en")
internal class Zinmanga(context: MangaLoaderContext) :
	MadaraParser(context, MangaParserSource.ZINMANGA, "zinmanga.net") {
	override val datePattern = "MM/dd/yyyy"
	override val withoutAjax = true
}
