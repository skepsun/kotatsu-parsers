package org.skepsun.kototoro.parsers.site.mangareader.id

import org.skepsun.kototoro.parsers.MangaLoaderContext
import org.skepsun.kototoro.parsers.MangaSourceParser
import org.skepsun.kototoro.parsers.model.MangaParserSource
import org.skepsun.kototoro.parsers.site.mangareader.MangaReaderParser
import java.util.*

@MangaSourceParser("MANGAYARO", "MangaYaro", "id")
internal class Mangayaro(context: MangaLoaderContext) :
	MangaReaderParser(
		context,
		MangaParserSource.MANGAYARO,
		"mangayaro.id",
		pageSize = 20,
		searchPageSize = 20,
	) {
	override val sourceLocale: Locale = Locale.ENGLISH
}
