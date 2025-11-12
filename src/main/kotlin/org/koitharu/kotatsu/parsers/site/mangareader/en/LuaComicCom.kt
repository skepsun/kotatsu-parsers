package org.skepsun.kototoro.parsers.site.mangareader.en

import org.skepsun.kototoro.parsers.MangaLoaderContext
import org.skepsun.kototoro.parsers.MangaSourceParser
import org.skepsun.kototoro.parsers.model.MangaListFilterCapabilities
import org.skepsun.kototoro.parsers.model.MangaParserSource
import org.skepsun.kototoro.parsers.site.mangareader.MangaReaderParser

@MangaSourceParser("LUACOMIC_COM", "luaComic.com", "en")
internal class LuaComicCom(context: MangaLoaderContext) :
	MangaReaderParser(context, MangaParserSource.LUACOMIC_COM, "luascans.com", pageSize = 20, searchPageSize = 10) {
	override val filterCapabilities: MangaListFilterCapabilities
		get() = super.filterCapabilities.copy(
			isTagsExclusionSupported = false,
		)
}
