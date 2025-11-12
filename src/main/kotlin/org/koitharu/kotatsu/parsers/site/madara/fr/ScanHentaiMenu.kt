package org.skepsun.kototoro.parsers.site.madara.fr

import org.skepsun.kototoro.parsers.MangaLoaderContext
import org.skepsun.kototoro.parsers.MangaSourceParser
import org.skepsun.kototoro.parsers.model.ContentType
import org.skepsun.kototoro.parsers.model.MangaParserSource
import org.skepsun.kototoro.parsers.site.madara.MadaraParser

@MangaSourceParser("SCANHENTAIMENU", "ScanHentai.Menu", "fr", ContentType.HENTAI)
internal class ScanHentaiMenu(context: MangaLoaderContext) :
	MadaraParser(context, MangaParserSource.SCANHENTAIMENU, "x-manga.org")
