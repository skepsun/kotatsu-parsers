package org.skepsun.kototoro.parsers.site.mangareader.id

import org.skepsun.kototoro.parsers.MangaLoaderContext
import org.skepsun.kototoro.parsers.MangaSourceParser
import org.skepsun.kototoro.parsers.model.MangaListFilterCapabilities
import org.skepsun.kototoro.parsers.model.MangaParserSource
import org.skepsun.kototoro.parsers.site.mangareader.MangaReaderParser
import java.util.Locale

@MangaSourceParser("MANHWALIST_ORG", "ManhwaList.org", "id")
internal class ManhwaListOrg(context: MangaLoaderContext) :
	MangaReaderParser(
		context,
		MangaParserSource.MANHWALIST_ORG,
		"isekaikomik.com",
		pageSize = 30,
		searchPageSize = 10,
	) {
	override val sourceLocale: Locale = Locale.ENGLISH
	override val listUrl = "/manga"
	override val filterCapabilities: MangaListFilterCapabilities
		get() = super.filterCapabilities.copy(
			isTagsExclusionSupported = false,
		)
}
