package org.skepsun.kototoro.parsers.site.pizzareader.fr

import org.skepsun.kototoro.parsers.MangaLoaderContext
import org.skepsun.kototoro.parsers.MangaSourceParser
import org.skepsun.kototoro.parsers.model.MangaParserSource
import org.skepsun.kototoro.parsers.site.pizzareader.PizzaReaderParser

@MangaSourceParser("FMTEAM", "FmTeam", "fr")
internal class FmTeam(context: MangaLoaderContext) :
	PizzaReaderParser(context, MangaParserSource.FMTEAM, "fmteam.fr") {
	override val ongoingFilter = "en cours"
	override val completedFilter = "terminé"
}
