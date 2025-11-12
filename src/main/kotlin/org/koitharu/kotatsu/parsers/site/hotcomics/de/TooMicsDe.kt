package org.skepsun.kototoro.parsers.site.hotcomics.de

import org.skepsun.kototoro.parsers.MangaLoaderContext
import org.skepsun.kototoro.parsers.MangaSourceParser
import org.skepsun.kototoro.parsers.model.MangaParserSource
import org.skepsun.kototoro.parsers.site.hotcomics.HotComicsParser

@MangaSourceParser("TOOMICSDE", "TooMicsDe", "de")
internal class TooMicsDe(context: MangaLoaderContext) :
	HotComicsParser(context, MangaParserSource.TOOMICSDE, "toomics.com/de") {
	override val isSearchSupported = false
	override val mangasUrl = "/webtoon/ranking/genre"
	override val selectMangas = "li > div.visual"
	override val selectMangaChapters = "li.normal_ep:has(.coin-type1)"
	override val selectTagsList = "div.genre_list li:not(.on) a"
	override val selectPages = "div[id^=load_image_] img"
	override val onePage = true
}
