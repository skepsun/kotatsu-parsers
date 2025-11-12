package org.skepsun.kototoro.parsers.site.mangareader.tr

import org.skepsun.kototoro.parsers.MangaLoaderContext
import org.skepsun.kototoro.parsers.MangaSourceParser
import org.skepsun.kototoro.parsers.model.MangaListFilterCapabilities
import org.skepsun.kototoro.parsers.model.MangaParserSource
import org.skepsun.kototoro.parsers.site.mangareader.MangaReaderParser
import org.skepsun.kototoro.parsers.Broken

@Broken
@MangaSourceParser("AFRODITSCANS", "AfroditScans", "tr")
internal class AfroditScans(context: MangaLoaderContext) :
	MangaReaderParser(context, MangaParserSource.AFRODITSCANS, "afroditscans.com", pageSize = 20, searchPageSize = 10) {
	override val datePattern = "MMM d, yyyy"
	override val isNetShieldProtected = true

	override val filterCapabilities: MangaListFilterCapabilities
		get() = super.filterCapabilities.copy(
			isTagsExclusionSupported = false,
		)
}
