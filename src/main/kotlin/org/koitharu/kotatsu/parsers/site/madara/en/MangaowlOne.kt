package org.skepsun.kototoro.parsers.site.madara.en

import org.skepsun.kototoro.parsers.Broken
import org.skepsun.kototoro.parsers.MangaLoaderContext
import org.skepsun.kototoro.parsers.MangaSourceParser
import org.skepsun.kototoro.parsers.model.ContentType
import org.skepsun.kototoro.parsers.model.MangaParserSource
import org.skepsun.kototoro.parsers.site.madara.MadaraParser

@Broken
@MangaSourceParser("MANGAOWL_ONE", "MangaOwl.one", "en", ContentType.HENTAI)
internal class MangaowlOne(context: MangaLoaderContext) :
	MadaraParser(context, MangaParserSource.MANGAOWL_ONE, "mangaowl.one") {
	override val postReq = true
}
