package org.skepsun.kototoro.parsers.site.mangareader.ar

import org.skepsun.kototoro.parsers.Broken
import org.skepsun.kototoro.parsers.MangaLoaderContext
import org.skepsun.kototoro.parsers.MangaSourceParser
import org.skepsun.kototoro.parsers.model.MangaListFilterCapabilities
import org.skepsun.kototoro.parsers.model.MangaParserSource
import org.skepsun.kototoro.parsers.site.mangareader.MangaReaderParser

@Broken
@MangaSourceParser("MANGANOON", "MangaNoon", "ar")
internal class MangaNoon(context: MangaLoaderContext) :
	MangaReaderParser(context, MangaParserSource.MANGANOON, "vrnoin.site", pageSize = 24, searchPageSize = 10) {

	override val filterCapabilities: MangaListFilterCapabilities
		get() = super.filterCapabilities.copy(
			isTagsExclusionSupported = false,
		)
}
