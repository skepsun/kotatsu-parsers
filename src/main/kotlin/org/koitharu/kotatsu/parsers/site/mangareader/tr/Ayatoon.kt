package org.skepsun.kototoro.parsers.site.mangareader.tr

import org.skepsun.kototoro.parsers.MangaLoaderContext
import org.skepsun.kototoro.parsers.MangaSourceParser
import org.skepsun.kototoro.parsers.model.MangaListFilterCapabilities
import org.skepsun.kototoro.parsers.model.MangaParserSource
import org.skepsun.kototoro.parsers.site.mangareader.MangaReaderParser

@MangaSourceParser("AYATOON", "AyaToon", "tr")
internal class Ayatoon(context: MangaLoaderContext) :
	MangaReaderParser(context, MangaParserSource.AYATOON, "ayatoon.com", pageSize = 20, searchPageSize = 20) {

	override val filterCapabilities: MangaListFilterCapabilities
		get() = super.filterCapabilities.copy(
			isTagsExclusionSupported = false,
		)
}
