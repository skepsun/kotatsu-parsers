package org.skepsun.kototoro.parsers.site.madara.en

import org.skepsun.kototoro.parsers.MangaLoaderContext
import org.skepsun.kototoro.parsers.MangaSourceParser
import org.skepsun.kototoro.parsers.model.MangaParserSource
import org.skepsun.kototoro.parsers.site.madara.MadaraParser

@MangaSourceParser("MANHUAZONE", "ManhuaZone", "en")
internal class ManhuaZone(context: MangaLoaderContext) :
	MadaraParser(context, MangaParserSource.MANHUAZONE, "manhuazone.org")
