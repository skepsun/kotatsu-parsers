package org.skepsun.kototoro.parsers.site.madara.tr

import org.skepsun.kototoro.parsers.MangaLoaderContext
import org.skepsun.kototoro.parsers.MangaSourceParser
import org.skepsun.kototoro.parsers.model.MangaParserSource
import org.skepsun.kototoro.parsers.site.madara.MadaraParser

@MangaSourceParser("ROMANTIKMANGA", "RomantikManga", "tr")
internal class RomantikManga(context: MangaLoaderContext) :
	MadaraParser(context, MangaParserSource.ROMANTIKMANGA, "webtoonhatti.club", 20)
