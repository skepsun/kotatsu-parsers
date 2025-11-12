package org.skepsun.kototoro.parsers.site.mangareader.en

import org.skepsun.kototoro.parsers.Broken
import org.skepsun.kototoro.parsers.MangaLoaderContext
import org.skepsun.kototoro.parsers.MangaSourceParser
import org.skepsun.kototoro.parsers.model.*
import org.skepsun.kototoro.parsers.site.mangareader.MangaReaderParser
import org.skepsun.kototoro.parsers.util.oneOrThrowIfMany
import org.skepsun.kototoro.parsers.util.parseHtml
import org.skepsun.kototoro.parsers.util.urlEncoded
import java.util.*

@Broken
@MangaSourceParser("ZAHARD", "Zahard", "en")
internal class Zahard(context: MangaLoaderContext) :
	MangaReaderParser(context, MangaParserSource.ZAHARD, "zahard.xyz", pageSize = 20, searchPageSize = 30) {

	override val listUrl = "/library"
	override val selectChapter = "#chapterlist > ul > a"
	override val selectPage = "div#chapter_imgs img"
	override val availableSortOrders: Set<SortOrder> = EnumSet.of(SortOrder.NEWEST)

	override val filterCapabilities: MangaListFilterCapabilities
		get() = super.filterCapabilities.copy(
			isMultipleTagsSupported = false,
			isTagsExclusionSupported = false,
		)

	override suspend fun getFilterOptions() = super.getFilterOptions().copy(
		availableStates = emptySet(),
	)

	override suspend fun getListPage(page: Int, order: SortOrder, filter: MangaListFilter): List<Manga> {
		val url = buildString {
			append("https://")
			append(domain)
			append(listUrl)
			append("?page=")
			append(page.toString())
			when {
				!filter.query.isNullOrEmpty() -> {
					append("&search=")
					append(filter.query.urlEncoded())
				}

				else -> {
					filter.tags.oneOrThrowIfMany()?.let {
						append("tag=")
						append(it.key)
					}
				}
			}
		}
		return parseMangaList(webClient.httpGet(url).parseHtml())
	}
}
