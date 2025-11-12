package org.skepsun.kototoro.parsers.site.madara.ar

import org.skepsun.kototoro.parsers.MangaLoaderContext
import org.skepsun.kototoro.parsers.MangaSourceParser
import org.skepsun.kototoro.parsers.model.MangaParserSource
import org.skepsun.kototoro.parsers.site.madara.MadaraParser

@MangaSourceParser("MANGALINKNET", "Link-Manga.com", "ar")
internal class MangaLinkNet(context: MangaLoaderContext) :
	MadaraParser(context, MangaParserSource.MANGALINKNET, "link-manga.com", pageSize = 10)
