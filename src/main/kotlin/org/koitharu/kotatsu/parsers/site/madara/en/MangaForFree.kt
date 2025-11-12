package org.skepsun.kototoro.parsers.site.madara.en

import org.skepsun.kototoro.parsers.MangaLoaderContext
import org.skepsun.kototoro.parsers.MangaSourceParser
import org.skepsun.kototoro.parsers.model.ContentType
import org.skepsun.kototoro.parsers.model.MangaParserSource
import org.skepsun.kototoro.parsers.site.madara.MadaraParser

@MangaSourceParser("MANGAFORFREE", "MangaForFree", "en", ContentType.HENTAI)
internal class MangaForFree(context: MangaLoaderContext) :
	MadaraParser(context, MangaParserSource.MANGAFORFREE, "mangaforfree.com", 10) {
	override val postReq = true
}
