package org.skepsun.kototoro.parsers.site.pizzareader.it

import org.skepsun.kototoro.parsers.MangaLoaderContext
import org.skepsun.kototoro.parsers.MangaSourceParser
import org.skepsun.kototoro.parsers.model.MangaParserSource
import org.skepsun.kototoro.parsers.site.pizzareader.PizzaReaderParser

@MangaSourceParser("HASTATEAM", "HastaTeamDdt", "it")
internal class HastaTeam(context: MangaLoaderContext) :
	PizzaReaderParser(context, MangaParserSource.HASTATEAM, "ddt.hastateam.com")
