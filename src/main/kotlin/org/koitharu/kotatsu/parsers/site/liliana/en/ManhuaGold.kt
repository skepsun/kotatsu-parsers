package org.skepsun.kototoro.parsers.site.liliana.en

import org.skepsun.kototoro.parsers.MangaLoaderContext
import org.skepsun.kototoro.parsers.MangaSourceParser
import org.skepsun.kototoro.parsers.model.MangaParserSource
import org.skepsun.kototoro.parsers.site.liliana.LilianaParser

@MangaSourceParser("MANHUAGOLD", "ManhuaGold", "en")
internal class ManhuaGold(context: MangaLoaderContext) :
	LilianaParser(context, MangaParserSource.MANHUAGOLD, "manhuagold.top")
