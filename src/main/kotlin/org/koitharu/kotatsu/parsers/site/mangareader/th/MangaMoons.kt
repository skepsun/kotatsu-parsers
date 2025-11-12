package org.skepsun.kototoro.parsers.site.mangareader.th

import org.skepsun.kototoro.parsers.Broken
import org.skepsun.kototoro.parsers.MangaLoaderContext
import org.skepsun.kototoro.parsers.MangaSourceParser
import org.skepsun.kototoro.parsers.model.MangaListFilterCapabilities
import org.skepsun.kototoro.parsers.model.MangaParserSource
import org.skepsun.kototoro.parsers.site.mangareader.MangaReaderParser

@Broken
@MangaSourceParser("MANGAMOONS", "MangaMoons", "th")
internal class MangaMoons(context: MangaLoaderContext) :
	MangaReaderParser(context, MangaParserSource.MANGAMOONS, "manga-moons.net", pageSize = 20, searchPageSize = 10) {
	override val filterCapabilities: MangaListFilterCapabilities
		get() = super.filterCapabilities.copy(
			isTagsExclusionSupported = false,
		)
}
