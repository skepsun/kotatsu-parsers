package org.skepsun.kototoro.parsers.site.mangareader.id

import org.skepsun.kototoro.parsers.Broken
import org.skepsun.kototoro.parsers.MangaLoaderContext
import org.skepsun.kototoro.parsers.MangaSourceParser
import org.skepsun.kototoro.parsers.model.ContentType
import org.skepsun.kototoro.parsers.model.MangaParserSource
import org.skepsun.kototoro.parsers.site.mangareader.MangaReaderParser
import java.util.*

@Broken
@MangaSourceParser("KOMIKLOKALCFD", "KomikLokal.mom", "id", ContentType.HENTAI)
internal class KomiklokalCfd(context: MangaLoaderContext) :
	MangaReaderParser(context, MangaParserSource.KOMIKLOKALCFD, "komikmu.icu", pageSize = 30, searchPageSize = 10) {
	override val sourceLocale: Locale = Locale.ENGLISH
}