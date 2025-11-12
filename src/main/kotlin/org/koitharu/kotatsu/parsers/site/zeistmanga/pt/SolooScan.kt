package org.skepsun.kototoro.parsers.site.zeistmanga.pt

import org.skepsun.kototoro.parsers.MangaLoaderContext
import org.skepsun.kototoro.parsers.MangaSourceParser
import org.skepsun.kototoro.parsers.model.MangaParserSource
import org.skepsun.kototoro.parsers.site.zeistmanga.ZeistMangaParser
import org.skepsun.kototoro.parsers.Broken

@Broken
@MangaSourceParser("SOLOOSCAN", "SolooScan", "pt")
internal class SolooScan(context: MangaLoaderContext) :
	ZeistMangaParser(context, MangaParserSource.SOLOOSCAN, "solooscan.blogspot.com") {
	override val mangaCategory = "Recentes"
	override val sateOngoing: String = "Lançando"
	override val sateFinished: String = "Completo"
	override val sateAbandoned: String = "Dropado"
}
