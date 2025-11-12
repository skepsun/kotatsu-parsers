package org.skepsun.kototoro.parsers.site.mangareader.id

import org.skepsun.kototoro.parsers.MangaLoaderContext
import org.skepsun.kototoro.parsers.MangaSourceParser
import org.skepsun.kototoro.parsers.model.ContentType
import org.skepsun.kototoro.parsers.model.MangaParserSource
import org.skepsun.kototoro.parsers.site.mangareader.MangaReaderParser
import java.util.*

@MangaSourceParser("MANHWAINDOICU", "KomikCinta", "id", ContentType.HENTAI)
internal class ManhwaIndoIcu(context: MangaLoaderContext) :
	MangaReaderParser(context, MangaParserSource.MANHWAINDOICU, "komikdewasa.art", pageSize = 30, searchPageSize = 10) {
	override val sourceLocale: Locale = Locale.ENGLISH
	override val listUrl = "/komik"
}
