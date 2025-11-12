package org.skepsun.kototoro.parsers.site.madara.en

import org.skepsun.kototoro.parsers.Broken
import org.skepsun.kototoro.parsers.MangaLoaderContext
import org.skepsun.kototoro.parsers.MangaSourceParser
import org.skepsun.kototoro.parsers.model.MangaParserSource
import org.skepsun.kototoro.parsers.site.madara.MadaraParser

@Broken
@MangaSourceParser("MANGACULTIVATOR", "MangaCultivator", "en")
internal class MangaCultivator(context: MangaLoaderContext) :
	MadaraParser(context, MangaParserSource.MANGACULTIVATOR, "mangacultivator.com", 10)
