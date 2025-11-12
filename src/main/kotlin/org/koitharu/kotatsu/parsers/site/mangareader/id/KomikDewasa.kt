package org.skepsun.kototoro.parsers.site.mangareader.id

import org.skepsun.kototoro.parsers.MangaLoaderContext
import org.skepsun.kototoro.parsers.MangaSourceParser
import org.skepsun.kototoro.parsers.model.ContentType
import org.skepsun.kototoro.parsers.model.MangaListFilterCapabilities
import org.skepsun.kototoro.parsers.model.MangaParserSource
import org.skepsun.kototoro.parsers.site.mangareader.MangaReaderParser
import java.util.*

@MangaSourceParser("KOMIKDEWASA_ONLINE", "KomikDewasa.Online", "id", ContentType.HENTAI)
internal class KomikDewasa(context: MangaLoaderContext) :
	MangaReaderParser(
		context,
		MangaParserSource.KOMIKDEWASA_ONLINE,
		"komikdewasa.art",
		pageSize = 20,
		searchPageSize = 10,
	) {
	override val sourceLocale: Locale = Locale.ENGLISH
	override val filterCapabilities: MangaListFilterCapabilities
		get() = super.filterCapabilities.copy(
			isTagsExclusionSupported = false,
		)
}
