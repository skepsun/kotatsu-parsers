package org.skepsun.kototoro.parsers.site.madara.tr

import org.skepsun.kototoro.parsers.Broken
import org.skepsun.kototoro.parsers.MangaLoaderContext
import org.skepsun.kototoro.parsers.MangaSourceParser
import org.skepsun.kototoro.parsers.model.MangaParserSource
import org.skepsun.kototoro.parsers.site.madara.MadaraParser

@Broken
@MangaSourceParser("ANISA_MANGA", "AnisaManga", "tr")
internal class AnisaManga(context: MangaLoaderContext) :
	MadaraParser(context, MangaParserSource.ANISA_MANGA, "anisamanga.com")
