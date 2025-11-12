package org.skepsun.kototoro.parsers.site.mangareader.id

import org.skepsun.kototoro.parsers.MangaLoaderContext
import org.skepsun.kototoro.parsers.MangaSourceParser
import org.skepsun.kototoro.parsers.model.MangaListFilterCapabilities
import org.skepsun.kototoro.parsers.model.MangaParserSource
import org.skepsun.kototoro.parsers.site.mangareader.MangaReaderParser
import org.skepsun.kototoro.parsers.Broken

@Broken // The website is either closed or constantly blocked
@MangaSourceParser("SIIKOMIK", "SiiKomik", "id")
internal class SiiKomik(context: MangaLoaderContext) :
	MangaReaderParser(context, MangaParserSource.SIIKOMIK, "siikomik.fun", pageSize = 20, searchPageSize = 10) {
	override val filterCapabilities: MangaListFilterCapabilities
		get() = super.filterCapabilities.copy(
			isTagsExclusionSupported = false,
			isSearchSupported = false,
		)
}

