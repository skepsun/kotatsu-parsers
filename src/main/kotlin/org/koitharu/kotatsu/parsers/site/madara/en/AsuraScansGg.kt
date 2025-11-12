package org.skepsun.kototoro.parsers.site.madara.en

import org.skepsun.kototoro.parsers.MangaLoaderContext
import org.skepsun.kototoro.parsers.MangaSourceParser
import org.skepsun.kototoro.parsers.model.MangaParserSource
import org.skepsun.kototoro.parsers.site.madara.MadaraParser

@MangaSourceParser("ASURASCANSGG", "AsuraScansGg", "en")
internal class AsuraScansGg(context: MangaLoaderContext) :
	MadaraParser(context, MangaParserSource.ASURASCANSGG, "asurascans.us")
