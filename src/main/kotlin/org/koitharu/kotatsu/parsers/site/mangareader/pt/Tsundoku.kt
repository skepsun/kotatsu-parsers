package org.skepsun.kototoro.parsers.site.mangareader.pt

import org.skepsun.kototoro.parsers.MangaLoaderContext
import org.skepsun.kototoro.parsers.MangaSourceParser
import org.skepsun.kototoro.parsers.model.MangaListFilterCapabilities
import org.skepsun.kototoro.parsers.model.MangaParserSource
import org.skepsun.kototoro.parsers.site.mangareader.MangaReaderParser

@MangaSourceParser("TSUNDOKU", "Tsundoku", "pt")
internal class Tsundoku(context: MangaLoaderContext) :
	MangaReaderParser(context, MangaParserSource.TSUNDOKU, "tsundoku.com.br", pageSize = 50, searchPageSize = 50) {
	override val datePattern = "MMM d, yyyy"
	override val filterCapabilities: MangaListFilterCapabilities
		get() = super.filterCapabilities.copy(
			isTagsExclusionSupported = false,
		)
}
