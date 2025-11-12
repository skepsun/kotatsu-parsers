package org.skepsun.kototoro.parsers.site.mangareader.es

import org.skepsun.kototoro.parsers.MangaLoaderContext
import org.skepsun.kototoro.parsers.MangaSourceParser
import org.skepsun.kototoro.parsers.model.MangaParserSource
import org.skepsun.kototoro.parsers.site.mangareader.MangaReaderParser
import org.skepsun.kototoro.parsers.Broken

@Broken
@MangaSourceParser("CARTELDEMANHWAS", "Cartel De Manhwas", "es")
internal class CartelDeManhwas(context: MangaLoaderContext) :
	MangaReaderParser(
		context,
		MangaParserSource.CARTELDEMANHWAS,
		"cartelmanhwas.net",
		pageSize = 20,
		searchPageSize = 20,
	) {
	override val listUrl = "/series"
	override val datePattern = "MMM d, yyyy"
}
