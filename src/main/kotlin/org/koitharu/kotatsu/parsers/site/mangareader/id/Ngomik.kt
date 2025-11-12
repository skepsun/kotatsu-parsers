package org.skepsun.kototoro.parsers.site.mangareader.id

import org.skepsun.kototoro.parsers.MangaLoaderContext
import org.skepsun.kototoro.parsers.MangaSourceParser
import org.skepsun.kototoro.parsers.model.MangaParserSource
import org.skepsun.kototoro.parsers.site.mangareader.MangaReaderParser
import java.util.*

@MangaSourceParser("NGOMIK", "Ngomik", "id")
internal class Ngomik(context: MangaLoaderContext) :
	MangaReaderParser(context, MangaParserSource.NGOMIK, "ngomik.mom", pageSize = 20, searchPageSize = 5) {
	override val sourceLocale: Locale = Locale.ENGLISH
}
