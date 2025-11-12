package org.skepsun.kototoro.parsers.site.pizzareader.fr

import org.skepsun.kototoro.parsers.MangaLoaderContext
import org.skepsun.kototoro.parsers.MangaSourceParser
import org.skepsun.kototoro.parsers.model.MangaParserSource
import org.skepsun.kototoro.parsers.site.pizzareader.PizzaReaderParser

@MangaSourceParser("HNISCANTRAD", "HniScantrad", "fr")
internal class HniScantrad(context: MangaLoaderContext) :
	PizzaReaderParser(context, MangaParserSource.HNISCANTRAD, "hni-scantrad.net") {
	override val ongoingFilter = "en cours"
	override val completedFilter = "terminé"
}
