package org.skepsun.kototoro.parsers.site.mangareader.ar

import org.skepsun.kototoro.parsers.MangaLoaderContext
import org.skepsun.kototoro.parsers.MangaSourceParser
import org.skepsun.kototoro.parsers.model.ContentType
import org.skepsun.kototoro.parsers.model.MangaParserSource
import org.skepsun.kototoro.parsers.site.mangareader.MangaReaderParser
import java.util.*

@MangaSourceParser("PEACHBL", "PeachBl", "ar", ContentType.HENTAI)
internal class PeachBl(context: MangaLoaderContext) :
	MangaReaderParser(context, MangaParserSource.PEACHBL, "peach-bl.com", pageSize = 20, searchPageSize = 10) {
	override val sourceLocale: Locale = Locale.ENGLISH
}
