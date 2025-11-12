package org.skepsun.kototoro.parsers.site.madara.en

import org.skepsun.kototoro.parsers.Broken
import org.skepsun.kototoro.parsers.MangaLoaderContext
import org.skepsun.kototoro.parsers.MangaSourceParser
import org.skepsun.kototoro.parsers.model.MangaParserSource
import org.skepsun.kototoro.parsers.site.madara.MadaraParser

@Broken
@MangaSourceParser("ZINMANGA_CC", "ZinManga.cc", "en")
internal class ZinMangaCc(context: MangaLoaderContext) :
	MadaraParser(context, MangaParserSource.ZINMANGA_CC, "zinmanga.cc")
