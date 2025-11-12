package org.skepsun.kototoro.parsers.site.mangareader.th

import org.skepsun.kototoro.parsers.MangaLoaderContext
import org.skepsun.kototoro.parsers.MangaSourceParser
import org.skepsun.kototoro.parsers.model.MangaListFilterCapabilities
import org.skepsun.kototoro.parsers.model.MangaParserSource
import org.skepsun.kototoro.parsers.site.mangareader.MangaReaderParser
import java.util.*

@MangaSourceParser("REAPERTRANS", "ReaperTrans", "th")
internal class ReaperTrans(context: MangaLoaderContext) :
	MangaReaderParser(context, MangaParserSource.REAPERTRANS, "reapertrans.com", pageSize = 30, searchPageSize = 14) {
	override val filterCapabilities: MangaListFilterCapabilities
		get() = super.filterCapabilities.copy(
			isTagsExclusionSupported = false,
		)
	override val sourceLocale: Locale = Locale.ENGLISH
}
