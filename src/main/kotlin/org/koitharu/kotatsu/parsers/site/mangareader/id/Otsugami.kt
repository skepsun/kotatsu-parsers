package org.skepsun.kototoro.parsers.site.mangareader.id

import org.skepsun.kototoro.parsers.Broken
import org.skepsun.kototoro.parsers.MangaLoaderContext
import org.skepsun.kototoro.parsers.MangaSourceParser
import org.skepsun.kototoro.parsers.model.MangaParserSource
import org.skepsun.kototoro.parsers.site.mangareader.MangaReaderParser
import java.util.*

@Broken
@MangaSourceParser("OTSUGAMI", "Otsugami", "id")
internal class Otsugami(context: MangaLoaderContext) :
	MangaReaderParser(context, MangaParserSource.OTSUGAMI, "otsugami.id", pageSize = 40, searchPageSize = 10) {
	override val sourceLocale: Locale = Locale.ENGLISH

}
