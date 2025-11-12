package org.skepsun.kototoro.parsers.site.madara.es

import org.skepsun.kototoro.parsers.Broken
import org.skepsun.kototoro.parsers.MangaLoaderContext
import org.skepsun.kototoro.parsers.MangaSourceParser
import org.skepsun.kototoro.parsers.model.MangaParserSource
import org.skepsun.kototoro.parsers.site.madara.MadaraParser

@Broken
@MangaSourceParser("HOUSEOFOTAKUS", "HouseOfOtakus", "es")
internal class HouseOfOtakus(context: MangaLoaderContext) :
	MadaraParser(context, MangaParserSource.HOUSEOFOTAKUS, "houseofotakus.xyz")
