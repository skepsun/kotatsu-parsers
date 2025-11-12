package org.skepsun.kototoro.parsers.site.zeistmanga.pt

import org.skepsun.kototoro.parsers.Broken
import org.skepsun.kototoro.parsers.MangaLoaderContext
import org.skepsun.kototoro.parsers.MangaSourceParser
import org.skepsun.kototoro.parsers.model.ContentType
import org.skepsun.kototoro.parsers.model.MangaParserSource
import org.skepsun.kototoro.parsers.site.zeistmanga.ZeistMangaParser

@Broken
@MangaSourceParser("ZSCANLATION", "ZScanlation", "pt", ContentType.HENTAI)
internal class ZScanlation(context: MangaLoaderContext) :
	ZeistMangaParser(context, MangaParserSource.ZSCANLATION, "www.zscanlation.com") {
	override val sateOngoing: String = "Em Lançamento"
	override val sateFinished: String = "Completo"
	override val sateAbandoned: String = "Dropado"
}
