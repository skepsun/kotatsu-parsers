package org.skepsun.kototoro.parsers.site.madara.tr

import org.skepsun.kototoro.parsers.Broken
import org.skepsun.kototoro.parsers.MangaLoaderContext
import org.skepsun.kototoro.parsers.MangaSourceParser
import org.skepsun.kototoro.parsers.model.MangaParserSource
import org.skepsun.kototoro.parsers.site.madara.MadaraParser

@Broken("Redirect to @WEBTOONHATTI")
@MangaSourceParser("CLOVERMANGA", "CloverManga", "tr")
internal class CloverManga(context: MangaLoaderContext) :
	MadaraParser(context, MangaParserSource.CLOVERMANGA, "webtoonhatti.me", 20)
