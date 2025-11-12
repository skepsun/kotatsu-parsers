package org.skepsun.kototoro.parsers.site.mangareader.tr

import org.skepsun.kototoro.parsers.MangaLoaderContext
import org.skepsun.kototoro.parsers.MangaSourceParser
import org.skepsun.kototoro.parsers.model.ContentType
import org.skepsun.kototoro.parsers.model.MangaListFilterCapabilities
import org.skepsun.kototoro.parsers.model.MangaParserSource
import org.skepsun.kototoro.parsers.site.mangareader.MangaReaderParser

@MangaSourceParser("ARCURAFANSUB", "ArcuraFansub", "tr", ContentType.HENTAI)
internal class ArcuraFansub(context: MangaLoaderContext) :
	MangaReaderParser(context, MangaParserSource.ARCURAFANSUB, "arcurafansub.com", pageSize = 20, searchPageSize = 10) {
	override val listUrl = "/seri"

	override val filterCapabilities: MangaListFilterCapabilities
		get() = super.filterCapabilities.copy(
			isTagsExclusionSupported = false,
		)
}
