package org.skepsun.kototoro.parsers.site.mangareader.id

import org.skepsun.kototoro.parsers.MangaLoaderContext
import org.skepsun.kototoro.parsers.MangaSourceParser
import org.skepsun.kototoro.parsers.model.MangaListFilterCapabilities
import org.skepsun.kototoro.parsers.model.MangaParserSource
import org.skepsun.kototoro.parsers.site.mangareader.MangaReaderParser
import java.util.*
import org.skepsun.kototoro.parsers.Broken

@Broken("Not dead, changed template")
@MangaSourceParser("WESTMANGA", "WestManga", "id")
internal class WestmangaParser(context: MangaLoaderContext) :
	MangaReaderParser(context, MangaParserSource.WESTMANGA, "westmanga.me", pageSize = 20, searchPageSize = 10) {
	override val sourceLocale: Locale = Locale.ENGLISH
	override val filterCapabilities: MangaListFilterCapabilities
		get() = super.filterCapabilities.copy(
			isTagsExclusionSupported = false,
		)
}
