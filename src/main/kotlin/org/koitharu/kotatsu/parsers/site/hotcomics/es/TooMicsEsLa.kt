package org.skepsun.kototoro.parsers.site.hotcomics.es

import org.skepsun.kototoro.parsers.MangaLoaderContext
import org.skepsun.kototoro.parsers.MangaSourceParser
import org.skepsun.kototoro.parsers.model.MangaParserSource
import org.skepsun.kototoro.parsers.site.hotcomics.HotComicsParser

@MangaSourceParser("TOOMICSESLA", "TooMicsEsLa", "es")
internal class TooMicsEsLa(context: MangaLoaderContext) :
	HotComicsParser(context, MangaParserSource.TOOMICSESLA, "toomics.com/mx") {
	override val isSearchSupported = false
	override val mangasUrl = "/webtoon/ranking/genre"
	override val selectMangas = "li > div.visual"
	override val selectMangaChapters = "li.normal_ep:has(.coin-type1)"
	override val selectTagsList = "div.genre_list li:not(.on) a"
	override val selectPages = "div[id^=load_image_] img"
	override val onePage = true
}
