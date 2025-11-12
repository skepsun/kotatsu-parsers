package org.skepsun.kototoro.parsers.site.madara.en

import org.skepsun.kototoro.parsers.MangaLoaderContext
import org.skepsun.kototoro.parsers.MangaSourceParser
import org.skepsun.kototoro.parsers.model.ContentType
import org.skepsun.kototoro.parsers.model.MangaParserSource
import org.skepsun.kototoro.parsers.site.madara.MadaraParser

@MangaSourceParser("HENTAIXCOMIC", "Hentai x Comic", "en", ContentType.HENTAI)
internal class HentaixComic(context: MangaLoaderContext) :
	MadaraParser(context, MangaParserSource.HENTAIXCOMIC, "hentaixcomic.com", 16) {
	override val postReq = true
}
