package org.skepsun.kototoro.parsers.site.mangareader.tr

import org.skepsun.kototoro.parsers.MangaLoaderContext
import org.skepsun.kototoro.parsers.MangaSourceParser
import org.skepsun.kototoro.parsers.model.MangaListFilterCapabilities
import org.skepsun.kototoro.parsers.model.MangaParserSource
import org.skepsun.kototoro.parsers.site.mangareader.MangaReaderParser

@MangaSourceParser("RAINDROPTEAMFAN", "Raindrop Fansub", "tr")
internal class Raindropteamfan(context: MangaLoaderContext) :
	MangaReaderParser(
		context,
		MangaParserSource.RAINDROPTEAMFAN,
		"www.raindropteamfan.com",
		pageSize = 25,
		searchPageSize = 10,
	) {
	override val filterCapabilities: MangaListFilterCapabilities
		get() = super.filterCapabilities.copy(
			isTagsExclusionSupported = false,
		)
}

