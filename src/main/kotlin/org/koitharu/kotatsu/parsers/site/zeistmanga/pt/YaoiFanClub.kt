package org.skepsun.kototoro.parsers.site.zeistmanga.pt

import org.skepsun.kototoro.parsers.MangaLoaderContext
import org.skepsun.kototoro.parsers.MangaSourceParser
import org.skepsun.kototoro.parsers.model.MangaParserSource
import org.skepsun.kototoro.parsers.site.zeistmanga.ZeistMangaParser

@MangaSourceParser("YAOIFANCLUB", "YaoiFanClub", "pt")
internal class YaoiFanClub(context: MangaLoaderContext) :
	ZeistMangaParser(context, MangaParserSource.YAOIFANCLUB, "www.yaoifanclub.com") {
	override val sateOngoing: String = "Ativo"
	override val sateFinished: String = "Completo"
	override val sateAbandoned: String = "Dropado"
}
