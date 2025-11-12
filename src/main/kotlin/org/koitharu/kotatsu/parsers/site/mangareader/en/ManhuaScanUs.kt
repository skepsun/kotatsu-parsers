package org.skepsun.kototoro.parsers.site.mangareader.en

import org.skepsun.kototoro.parsers.MangaLoaderContext
import org.skepsun.kototoro.parsers.MangaSourceParser
import org.skepsun.kototoro.parsers.model.ContentType
import org.skepsun.kototoro.parsers.model.MangaParserSource
import org.skepsun.kototoro.parsers.site.mangareader.MangaReaderParser

@MangaSourceParser("MANHUASCANUS", "ManhuaScan.Us", "en", ContentType.HENTAI)
internal class ManhuaScanUs(context: MangaLoaderContext) :
	MangaReaderParser(context, MangaParserSource.MANHUASCANUS, "manhuascan.us", pageSize = 30, searchPageSize = 30) {
	override val datePattern = "dd-MM-yyyy"
	override val listUrl = "/manga-list"
}
