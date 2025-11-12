package org.skepsun.kototoro.parsers.site.gattsu.pt

import org.skepsun.kototoro.parsers.MangaLoaderContext
import org.skepsun.kototoro.parsers.MangaSourceParser
import org.skepsun.kototoro.parsers.model.ContentType
import org.skepsun.kototoro.parsers.model.MangaParserSource
import org.skepsun.kototoro.parsers.site.gattsu.GattsuParser

@MangaSourceParser("HENTAISEASON", "HentaiSeason", "pt", ContentType.HENTAI)
internal class HentaiSeason(context: MangaLoaderContext) :
	GattsuParser(context, MangaParserSource.HENTAISEASON, "hentaiseason.com")
