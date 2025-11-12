package org.skepsun.kototoro.parsers.site.mangareader.th

import org.skepsun.kototoro.parsers.MangaLoaderContext
import org.skepsun.kototoro.parsers.MangaSourceParser
import org.skepsun.kototoro.parsers.model.ContentType
import org.skepsun.kototoro.parsers.model.MangaListFilterCapabilities
import org.skepsun.kototoro.parsers.model.MangaParserSource
import org.skepsun.kototoro.parsers.site.mangareader.MangaReaderParser

@MangaSourceParser("TOOMTAMMANGA", "ToomtamManga", "th", ContentType.HENTAI)
internal class ToomtamManga(context: MangaLoaderContext) :
	MangaReaderParser(
		context,
		MangaParserSource.TOOMTAMMANGA,
		"toomtam-manga.com",
		pageSize = 30,
		searchPageSize = 28,
	) {
	override val filterCapabilities: MangaListFilterCapabilities
		get() = super.filterCapabilities.copy(
			isTagsExclusionSupported = false,
		)
}
