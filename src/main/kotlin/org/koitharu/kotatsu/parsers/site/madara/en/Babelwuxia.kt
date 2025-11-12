package org.skepsun.kototoro.parsers.site.madara.en

import org.skepsun.kototoro.parsers.MangaLoaderContext
import org.skepsun.kototoro.parsers.MangaSourceParser
import org.skepsun.kototoro.parsers.model.MangaParserSource
import org.skepsun.kototoro.parsers.site.madara.MadaraParser

@MangaSourceParser("BABELWUXIA", "Babelwuxia", "en")
internal class Babelwuxia(context: MangaLoaderContext) :
	MadaraParser(context, MangaParserSource.BABELWUXIA, "babelwuxia.com")
