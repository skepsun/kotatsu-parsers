package org.skepsun.kototoro.parsers.site.mangareader.en

import org.skepsun.kototoro.parsers.MangaLoaderContext
import org.skepsun.kototoro.parsers.MangaSourceParser
import org.skepsun.kototoro.parsers.model.MangaListFilterCapabilities
import org.skepsun.kototoro.parsers.model.MangaParserSource
import org.skepsun.kototoro.parsers.site.mangareader.MangaReaderParser

@MangaSourceParser("MANHWAFREAKE", "ManhwaFreake", "en")
internal class ManhwaFreake(context: MangaLoaderContext) :
	MangaReaderParser(context, MangaParserSource.MANHWAFREAKE, "manhwafreake.com", pageSize = 20, searchPageSize = 10) {
	override val listUrl = "/series"

	override val filterCapabilities: MangaListFilterCapabilities
		get() = super.filterCapabilities.copy(
			isTagsExclusionSupported = false,
		)
}
