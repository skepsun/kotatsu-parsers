package org.skepsun.kototoro.parsers.site.mangareader.pt

import org.skepsun.kototoro.parsers.MangaLoaderContext
import org.skepsun.kototoro.parsers.MangaSourceParser
import org.skepsun.kototoro.parsers.model.MangaListFilterCapabilities
import org.skepsun.kototoro.parsers.model.MangaParserSource
import org.skepsun.kototoro.parsers.site.mangareader.MangaReaderParser

@MangaSourceParser("DEMONSECT", "DemonSect", "pt")
internal class DemonSect(context: MangaLoaderContext) :
	MangaReaderParser(context, MangaParserSource.DEMONSECT, "seitacelestial.com", pageSize = 20, searchPageSize = 10) {
	override val listUrl = "/comics"

	override val filterCapabilities: MangaListFilterCapabilities
		get() = super.filterCapabilities.copy(
			isTagsExclusionSupported = false,
		)
}
