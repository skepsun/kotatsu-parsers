package org.skepsun.kototoro.parsers.site.mangareader.ja

import org.skepsun.kototoro.parsers.MangaLoaderContext
import org.skepsun.kototoro.parsers.MangaSourceParser
import org.skepsun.kototoro.parsers.model.MangaListFilterCapabilities
import org.skepsun.kototoro.parsers.model.MangaParserSource
import org.skepsun.kototoro.parsers.site.mangareader.MangaReaderParser
import java.util.*

@MangaSourceParser("MANGAJP", "MangaJp", "ja")
internal class MangaJp(context: MangaLoaderContext) :
	MangaReaderParser(context, MangaParserSource.MANGAJP, "mangajp.top", pageSize = 54, searchPageSize = 10) {
	override val sourceLocale: Locale = Locale.ENGLISH
	override val filterCapabilities: MangaListFilterCapabilities
		get() = super.filterCapabilities.copy(
			isTagsExclusionSupported = false,
		)
}
