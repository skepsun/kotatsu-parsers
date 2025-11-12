package org.skepsun.kototoro.parsers.site.zeistmanga.tr

import org.skepsun.kototoro.parsers.MangaLoaderContext
import org.skepsun.kototoro.parsers.MangaSourceParser
import org.skepsun.kototoro.parsers.model.ContentType
import org.skepsun.kototoro.parsers.model.MangaParserSource
import org.skepsun.kototoro.parsers.site.zeistmanga.ZeistMangaParser

@MangaSourceParser("SHADOWCEVIRI", "ShadowCeviri", "tr", ContentType.COMICS)
internal class ShadowCeviri(context: MangaLoaderContext) :
	ZeistMangaParser(context, MangaParserSource.SHADOWCEVIRI, "shadowceviri.blogspot.com") {
	override val sateOngoing: String = "Devam Ediyor"
	override val sateFinished: String = "Tamamlandı"
	override val sateAbandoned: String = "Güncel"
}
