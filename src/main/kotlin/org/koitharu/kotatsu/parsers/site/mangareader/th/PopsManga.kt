package org.skepsun.kototoro.parsers.site.mangareader.th

import org.skepsun.kototoro.parsers.MangaLoaderContext
import org.skepsun.kototoro.parsers.MangaSourceParser
import org.skepsun.kototoro.parsers.model.MangaListFilterCapabilities
import org.skepsun.kototoro.parsers.model.MangaParserSource
import org.skepsun.kototoro.parsers.site.mangareader.MangaReaderParser

@MangaSourceParser("POPSMANGA", "PopsManga", "th")
internal class PopsManga(context: MangaLoaderContext) :
	MangaReaderParser(context, MangaParserSource.POPSMANGA, "popsmanga.com", pageSize = 20, searchPageSize = 14) {
	override val filterCapabilities: MangaListFilterCapabilities
		get() = super.filterCapabilities.copy(
			isTagsExclusionSupported = false,
		)
}
