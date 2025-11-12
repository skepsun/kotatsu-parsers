package org.skepsun.kototoro.parsers.site.madara.id

import org.skepsun.kototoro.parsers.MangaLoaderContext
import org.skepsun.kototoro.parsers.MangaSourceParser
import org.skepsun.kototoro.parsers.model.MangaParserSource
import org.skepsun.kototoro.parsers.site.madara.MadaraParser
import java.util.*

@MangaSourceParser("POJOKMANGA", "PojokManga", "id")
internal class PojokManga(context: MangaLoaderContext) :
	MadaraParser(context, MangaParserSource.POJOKMANGA, "pojokmanga.info") {
	override val tagPrefix = "komik-genre/"
	override val listUrl = "komik/"
	override val datePattern = "MMM d, yyyy"
	override val sourceLocale: Locale = Locale.ENGLISH
}
