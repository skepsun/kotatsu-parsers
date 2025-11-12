package org.skepsun.kototoro.parsers.site.zeistmanga.pt

import org.skepsun.kototoro.parsers.MangaLoaderContext
import org.skepsun.kototoro.parsers.MangaSourceParser
import org.skepsun.kototoro.parsers.model.MangaParserSource
import org.skepsun.kototoro.parsers.model.MangaTag
import org.skepsun.kototoro.parsers.site.zeistmanga.ZeistMangaParser

@MangaSourceParser("RAYSSCAN", "RaysScan", "pt")
internal class RaysScan(context: MangaLoaderContext) :
	ZeistMangaParser(context, MangaParserSource.RAYSSCAN, "raysscan.blogspot.com") {

	override suspend fun getFilterOptions() = super.getFilterOptions().copy(
		availableStates = emptySet(),
	)

	override suspend fun fetchAvailableTags(): Set<MangaTag> = emptySet()
}


