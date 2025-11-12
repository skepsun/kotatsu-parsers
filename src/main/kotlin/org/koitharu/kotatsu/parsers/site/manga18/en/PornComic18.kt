package org.skepsun.kototoro.parsers.site.manga18.en

import org.skepsun.kototoro.parsers.MangaLoaderContext
import org.skepsun.kototoro.parsers.MangaSourceParser
import org.skepsun.kototoro.parsers.model.ContentType
import org.skepsun.kototoro.parsers.model.MangaParserSource
import org.skepsun.kototoro.parsers.site.manga18.Manga18Parser

@MangaSourceParser("PORNCOMIC18", "18PornComic", "en", ContentType.HENTAI)
internal class PornComic18(context: MangaLoaderContext) :
	Manga18Parser(context, MangaParserSource.PORNCOMIC18, "18porncomic.com") {
	override val selectTag = "div.item:not(.info_label) div.info_value a"
}
