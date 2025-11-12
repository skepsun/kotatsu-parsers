package org.skepsun.kototoro.parsers.site.pizzareader.it

import org.skepsun.kototoro.parsers.MangaLoaderContext
import org.skepsun.kototoro.parsers.MangaSourceParser
import org.skepsun.kototoro.parsers.model.MangaParserSource
import org.skepsun.kototoro.parsers.site.pizzareader.PizzaReaderParser

@MangaSourceParser("TUTTOANIMEMANGA", "TuttoAnimeManga", "it")
internal class TuttoAnimeManga(context: MangaLoaderContext) :
	PizzaReaderParser(context, MangaParserSource.TUTTOANIMEMANGA, "tuttoanimemanga.net") {
	override val completedFilter = "completato"
}
