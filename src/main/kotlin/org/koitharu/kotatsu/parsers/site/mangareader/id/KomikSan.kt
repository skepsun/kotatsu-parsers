package org.skepsun.kototoro.parsers.site.mangareader.id

import org.skepsun.kototoro.parsers.Broken
import org.skepsun.kototoro.parsers.MangaLoaderContext
import org.skepsun.kototoro.parsers.MangaSourceParser
import org.skepsun.kototoro.parsers.model.*
import org.skepsun.kototoro.parsers.site.mangareader.MangaReaderParser
import org.skepsun.kototoro.parsers.util.oneOrThrowIfMany
import org.skepsun.kototoro.parsers.util.parseHtml
import org.skepsun.kototoro.parsers.util.urlEncoded

@Broken
@MangaSourceParser("KOMIKSAN", "KomikSan", "id")
internal class KomikSan(context: MangaLoaderContext) :
	MangaReaderParser(context, MangaParserSource.KOMIKSAN, "komiksan.link", pageSize = 40, searchPageSize = 10) {

	override val selectMangaListImg = "img.attachment-medium"
	override val listUrl = "/list"
	override val datePattern = "MMM d, yyyy"
	override val filterCapabilities: MangaListFilterCapabilities
		get() = super.filterCapabilities.copy(
			isTagsExclusionSupported = false,
		)

	override suspend fun getListPage(page: Int, order: SortOrder, filter: MangaListFilter): List<Manga> {
		val url = buildString {
			append("https://")
			append(domain)
			when {

				!filter.query.isNullOrEmpty() -> {
					append("/search?search=")
					append(filter.query.urlEncoded())
					append("&page=")
					append(page.toString())
				}

				else -> {
					append(listUrl)

					append("/?order=")
					append(
						when (order) {
							SortOrder.ALPHABETICAL -> "title"
							SortOrder.ALPHABETICAL_DESC -> "titlereverse"
							SortOrder.NEWEST -> "latest"
							SortOrder.POPULARITY -> "popular"
							SortOrder.UPDATED -> "update"
							else -> ""
						},
					)

					val tagKey = "genre[]".urlEncoded()
					val tagQuery =
						if (filter.tags.isEmpty()) ""
						else filter.tags.joinToString(separator = "&", prefix = "&") { "$tagKey=${it.key}" }
					append(tagQuery)

					if (filter.states.isNotEmpty()) {
						filter.states.oneOrThrowIfMany()?.let {
							append("&status=")
							when (it) {
								MangaState.ONGOING -> append("ongoing")
								MangaState.FINISHED -> append("completed")
								MangaState.PAUSED -> append("hiatus")
								else -> append("")
							}
						}
					}

					append("&page=")
					append(page.toString())
				}
			}
		}
		return parseMangaList(webClient.httpGet(url).parseHtml())
	}
}
