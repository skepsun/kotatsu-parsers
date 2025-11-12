package org.skepsun.kototoro.parsers.core

import androidx.annotation.VisibleForTesting
import org.skepsun.kototoro.parsers.InternalParsersApi
import org.skepsun.kototoro.parsers.MangaLoaderContext
import org.skepsun.kototoro.parsers.model.Manga
import org.skepsun.kototoro.parsers.model.MangaListFilter
import org.skepsun.kototoro.parsers.model.MangaParserSource
import org.skepsun.kototoro.parsers.model.SortOrder
import org.skepsun.kototoro.parsers.util.Paginator

@InternalParsersApi
public abstract class PagedMangaParser(
	context: MangaLoaderContext,
	source: MangaParserSource,
	@VisibleForTesting(otherwise = VisibleForTesting.PROTECTED) @JvmField public val pageSize: Int,
	searchPageSize: Int = pageSize,
) : AbstractMangaParser(context, source) {

	@JvmField
	protected val paginator: Paginator = Paginator(pageSize)

	@JvmField
	protected val searchPaginator: Paginator = Paginator(searchPageSize)

	final override suspend fun getList(offset: Int, order: SortOrder, filter: MangaListFilter): List<Manga> {
		return getList(
			paginator = if (filter.query.isNullOrEmpty()) {
				paginator
			} else {
				searchPaginator
			},
			offset = offset,
			order = order,
			filter = filter,
		)
	}

	public abstract suspend fun getListPage(page: Int, order: SortOrder, filter: MangaListFilter): List<Manga>

	protected fun setFirstPage(firstPage: Int, firstPageForSearch: Int = firstPage) {
		paginator.firstPage = firstPage
		searchPaginator.firstPage = firstPageForSearch
	}

	private suspend fun getList(
		paginator: Paginator,
		offset: Int,
		order: SortOrder,
		filter: MangaListFilter,
	): List<Manga> {
		val page = paginator.getPage(offset)
		val list = getListPage(page, order, filter)
		paginator.onListReceived(offset, page, list.size)
		return list
	}
}
