package org.skepsun.kototoro.parsers.site.mangareader.fr

import org.skepsun.kototoro.parsers.Broken
import org.skepsun.kototoro.parsers.MangaLoaderContext
import org.skepsun.kototoro.parsers.MangaSourceParser
import org.skepsun.kototoro.parsers.model.MangaListFilterCapabilities
import org.skepsun.kototoro.parsers.model.MangaParserSource
import org.skepsun.kototoro.parsers.site.mangareader.MangaReaderParser
import org.skepsun.kototoro.parsers.util.insertCookies

@Broken
@MangaSourceParser("ETHERALRADIANCE", "EtheralRadiance", "fr")
internal class EtheralRadiance(context: MangaLoaderContext) :
	MangaReaderParser(
		context,
		MangaParserSource.ETHERALRADIANCE,
		"www.etheralradiance.eu",
		pageSize = 20,
		searchPageSize = 10,
	) {

	override val filterCapabilities: MangaListFilterCapabilities
		get() = super.filterCapabilities.copy(
			isTagsExclusionSupported = false,
		)

	init {
		context.cookieJar.insertCookies(
			domain,
			"_lscache_vary=1;",
		)
	}
}
