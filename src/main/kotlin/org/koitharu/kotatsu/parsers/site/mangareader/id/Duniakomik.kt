package org.skepsun.kototoro.parsers.site.mangareader.id

import org.skepsun.kototoro.parsers.Broken
import org.skepsun.kototoro.parsers.MangaLoaderContext
import org.skepsun.kototoro.parsers.MangaSourceParser
import org.skepsun.kototoro.parsers.model.ContentType
import org.skepsun.kototoro.parsers.model.MangaParserSource
import org.skepsun.kototoro.parsers.site.mangareader.MangaReaderParser

@Broken
@MangaSourceParser("DUNIAKOMIK", "DuniaKomik", "id", ContentType.HENTAI)
internal class Duniakomik(context: MangaLoaderContext) :
	MangaReaderParser(context, MangaParserSource.DUNIAKOMIK, "duniakomik.org", pageSize = 12, searchPageSize = 12) {
	override val datePattern = "MMM d, yyyy"
}