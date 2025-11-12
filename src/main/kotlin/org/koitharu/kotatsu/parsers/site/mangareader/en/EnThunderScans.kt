package org.skepsun.kototoro.parsers.site.mangareader.en

import org.skepsun.kototoro.parsers.MangaLoaderContext
import org.skepsun.kototoro.parsers.MangaSourceParser
import org.skepsun.kototoro.parsers.model.MangaListFilterCapabilities
import org.skepsun.kototoro.parsers.model.MangaParserSource
import org.skepsun.kototoro.parsers.site.mangareader.MangaReaderParser

@MangaSourceParser("ENTHUNDERSCANS", "EnThunderScans", "en")
internal class EnThunderScans(context: MangaLoaderContext) :
	MangaReaderParser(
		context,
		MangaParserSource.ENTHUNDERSCANS,
		"en-thunderscans.com",
		pageSize = 30,
		searchPageSize = 10,
	) {
	override val listUrl = "/comics"

	override val filterCapabilities: MangaListFilterCapabilities
		get() = super.filterCapabilities.copy(
			isTagsExclusionSupported = false,
		)
}
