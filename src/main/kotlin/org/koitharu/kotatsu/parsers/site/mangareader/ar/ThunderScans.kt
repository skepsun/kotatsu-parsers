package org.skepsun.kototoro.parsers.site.mangareader.ar

import org.skepsun.kototoro.parsers.MangaLoaderContext
import org.skepsun.kototoro.parsers.MangaSourceParser
import org.skepsun.kototoro.parsers.model.MangaListFilterCapabilities
import org.skepsun.kototoro.parsers.model.MangaParserSource
import org.skepsun.kototoro.parsers.site.mangareader.MangaReaderParser

@MangaSourceParser("THUNDERSCANS", "ThunderScans", "ar")
internal class ThunderScans(context: MangaLoaderContext) :
	MangaReaderParser(
		context,
		MangaParserSource.THUNDERSCANS,
		"lavascans.com",
		pageSize = 32,
		searchPageSize = 10,
	) {
	override val filterCapabilities: MangaListFilterCapabilities
		get() = super.filterCapabilities.copy(
			isTagsExclusionSupported = false,
		)
	override val selectChapter = ".eplister > ul > li"
}
