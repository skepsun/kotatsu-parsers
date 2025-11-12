package org.skepsun.kototoro.parsers.site.madara.pt

import org.skepsun.kototoro.parsers.MangaLoaderContext
import org.skepsun.kototoro.parsers.MangaSourceParser
import org.skepsun.kototoro.parsers.model.ContentType
import org.skepsun.kototoro.parsers.model.MangaParserSource
import org.skepsun.kototoro.parsers.site.madara.MadaraParser

@MangaSourceParser("PUSSYSUSSYTOONS", "PussySussyToons", "pt", ContentType.HENTAI)
internal class PussySussyToons(context: MangaLoaderContext) :
	MadaraParser(context, MangaParserSource.PUSSYSUSSYTOONS, "pussy.sussytoons.com")
