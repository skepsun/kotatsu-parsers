package org.skepsun.kototoro.parsers.site.mangareader.id

import org.skepsun.kototoro.parsers.MangaLoaderContext
import org.skepsun.kototoro.parsers.MangaSourceParser
import org.skepsun.kototoro.parsers.model.ContentType
import org.skepsun.kototoro.parsers.model.MangaListFilterCapabilities
import org.skepsun.kototoro.parsers.model.MangaParserSource
import org.skepsun.kototoro.parsers.site.mangareader.MangaReaderParser

@MangaSourceParser("MANHWALAND_INK", "ManhwaLand.ink", "id", ContentType.HENTAI)
internal class ManhwaLandInk(context: MangaLoaderContext) :
	MangaReaderParser(
		context,
		MangaParserSource.MANHWALAND_INK,
		"manhwaland.asia",
		pageSize = 20,
		searchPageSize = 10,
	) {
	override val filterCapabilities: MangaListFilterCapabilities
		get() = super.filterCapabilities.copy(
			isTagsExclusionSupported = false,
		)
	override val datePattern = "MMM d, yyyy"
}
