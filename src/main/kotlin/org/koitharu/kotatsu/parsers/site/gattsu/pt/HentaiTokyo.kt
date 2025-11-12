package org.skepsun.kototoro.parsers.site.gattsu.pt

import org.skepsun.kototoro.parsers.MangaLoaderContext
import org.skepsun.kototoro.parsers.MangaSourceParser
import org.skepsun.kototoro.parsers.model.ContentType
import org.skepsun.kototoro.parsers.model.MangaParserSource
import org.skepsun.kototoro.parsers.site.gattsu.GattsuParser

@MangaSourceParser("HENTAITOKYO", "HentaiTokyo", "pt", ContentType.HENTAI)
internal class HentaiTokyo(context: MangaLoaderContext) :
	GattsuParser(context, MangaParserSource.HENTAITOKYO, "hentaitokyo.net") {
	override val tagUrl = "tags"
}
