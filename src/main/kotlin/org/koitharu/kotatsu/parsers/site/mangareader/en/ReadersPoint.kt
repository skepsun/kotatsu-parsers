package org.skepsun.kototoro.parsers.site.mangareader.en

import org.skepsun.kototoro.parsers.Broken
import org.skepsun.kototoro.parsers.MangaLoaderContext
import org.skepsun.kototoro.parsers.MangaSourceParser
import org.skepsun.kototoro.parsers.model.MangaListFilterCapabilities
import org.skepsun.kototoro.parsers.model.MangaParserSource
import org.skepsun.kototoro.parsers.site.mangareader.MangaReaderParser

@Broken
@MangaSourceParser("READERSPOINT", "ReadersPoint", "en")
internal class ReadersPoint(context: MangaLoaderContext) :
	MangaReaderParser(context, MangaParserSource.READERSPOINT, "qscomics.org", pageSize = 20, searchPageSize = 10) {
	override val listUrl = "/series"
	override val filterCapabilities: MangaListFilterCapabilities
		get() = super.filterCapabilities.copy(
			isTagsExclusionSupported = false,
		)
}
