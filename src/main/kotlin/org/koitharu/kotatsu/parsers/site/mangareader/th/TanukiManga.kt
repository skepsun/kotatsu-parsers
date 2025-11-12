package org.skepsun.kototoro.parsers.site.mangareader.th

import org.skepsun.kototoro.parsers.MangaLoaderContext
import org.skepsun.kototoro.parsers.MangaSourceParser
import org.skepsun.kototoro.parsers.model.MangaListFilterCapabilities
import org.skepsun.kototoro.parsers.model.MangaParserSource
import org.skepsun.kototoro.parsers.site.mangareader.MangaReaderParser

@MangaSourceParser("TANUKIMANGA", "TanukiManga", "th")
internal class TanukiManga(context: MangaLoaderContext) :
	MangaReaderParser(
		context,
		MangaParserSource.TANUKIMANGA,
		"www.tanuki-manga.com",
		pageSize = 40,
		searchPageSize = 10,
	) {
	override val filterCapabilities: MangaListFilterCapabilities
		get() = super.filterCapabilities.copy(
			isTagsExclusionSupported = false,
		)
}
