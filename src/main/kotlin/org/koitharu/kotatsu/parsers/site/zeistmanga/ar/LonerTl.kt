package org.skepsun.kototoro.parsers.site.zeistmanga.ar

import org.skepsun.kototoro.parsers.MangaLoaderContext
import org.skepsun.kototoro.parsers.MangaSourceParser
import org.skepsun.kototoro.parsers.model.MangaParserSource
import org.skepsun.kototoro.parsers.site.zeistmanga.ZeistMangaParser

@MangaSourceParser("LONERTL", "LonerTranslations", "ar")
internal class LonerTl(context: MangaLoaderContext) :
	ZeistMangaParser(context, MangaParserSource.LONERTL, "loner-tl.blogspot.com") {
	override val sateOngoing: String = "مستمرة"
	override val sateFinished: String = "مكتملة"
	override val sateAbandoned: String = "متوقفة"
}
