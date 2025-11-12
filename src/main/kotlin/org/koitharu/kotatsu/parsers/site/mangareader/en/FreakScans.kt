package org.skepsun.kototoro.parsers.site.mangareader.en

import org.skepsun.kototoro.parsers.Broken
import org.skepsun.kototoro.parsers.MangaLoaderContext
import org.skepsun.kototoro.parsers.MangaSourceParser
import org.skepsun.kototoro.parsers.model.MangaListFilterCapabilities
import org.skepsun.kototoro.parsers.model.MangaParserSource
import org.skepsun.kototoro.parsers.site.mangareader.MangaReaderParser

@Broken
@MangaSourceParser("FREAKSCANS", "FreakScans", "en")
internal class FreakScans(context: MangaLoaderContext) :
	MangaReaderParser(context, MangaParserSource.FREAKSCANS, "freakscans.com", pageSize = 20, searchPageSize = 20) {
	override val datePattern = "MMM d, yyyy"

	override val filterCapabilities: MangaListFilterCapabilities
		get() = super.filterCapabilities.copy(
			isTagsExclusionSupported = false,
		)
}
