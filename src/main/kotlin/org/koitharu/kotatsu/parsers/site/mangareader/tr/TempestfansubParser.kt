package org.skepsun.kototoro.parsers.site.mangareader.tr

import org.skepsun.kototoro.parsers.Broken
import org.skepsun.kototoro.parsers.MangaLoaderContext
import org.skepsun.kototoro.parsers.MangaSourceParser
import org.skepsun.kototoro.parsers.model.MangaListFilterCapabilities
import org.skepsun.kototoro.parsers.model.MangaParserSource
import org.skepsun.kototoro.parsers.site.mangareader.MangaReaderParser

@Broken
@MangaSourceParser("TEMPESTFANSUB", "TempestFansub.Com", "tr")
internal class TempestfansubParser(context: MangaLoaderContext) :
	MangaReaderParser(
		context,
		MangaParserSource.TEMPESTFANSUB,
		"tempestscans.net",
		pageSize = 25,
		searchPageSize = 40,
	) {
	override val filterCapabilities: MangaListFilterCapabilities
		get() = super.filterCapabilities.copy(
			isTagsExclusionSupported = false,
		)
}
