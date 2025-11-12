package org.skepsun.kototoro.parsers.site.zmanga.id

import org.skepsun.kototoro.parsers.Broken
import org.skepsun.kototoro.parsers.MangaLoaderContext
import org.skepsun.kototoro.parsers.MangaSourceParser
import org.skepsun.kototoro.parsers.model.ContentType
import org.skepsun.kototoro.parsers.model.MangaParserSource
import org.skepsun.kototoro.parsers.site.zmanga.ZMangaParser

@Broken("Redirect to @MANGASUSUKU")
@MangaSourceParser("KOMIKINDO_INFO", "KomikIndo.info", "id", ContentType.HENTAI)
internal class KomikIndoInfo(context: MangaLoaderContext) :
	ZMangaParser(context, MangaParserSource.KOMIKINDO_INFO, "mangasusuku.com") {
	override val datePattern = "dd MMM yyyy"
}
