package org.skepsun.kototoro.parsers.site.madara.ar

import org.skepsun.kototoro.parsers.MangaLoaderContext
import org.skepsun.kototoro.parsers.MangaSourceParser
import org.skepsun.kototoro.parsers.model.MangaParserSource
import org.skepsun.kototoro.parsers.site.madara.MadaraParser

@MangaSourceParser("MANGAROSE", "MangaRose", "ar")
internal class MangaRose(context: MangaLoaderContext) :
	MadaraParser(context, MangaParserSource.MANGAROSE, "mangarose.net", pageSize = 20)
