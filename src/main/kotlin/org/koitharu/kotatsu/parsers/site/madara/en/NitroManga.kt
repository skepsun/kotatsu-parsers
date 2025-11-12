package org.skepsun.kototoro.parsers.site.madara.en

import org.skepsun.kototoro.parsers.MangaLoaderContext
import org.skepsun.kototoro.parsers.MangaSourceParser
import org.skepsun.kototoro.parsers.model.MangaParserSource
import org.skepsun.kototoro.parsers.site.madara.MadaraParser

@MangaSourceParser("NITROMANGA", "NitroManga", "en")
internal class NitroManga(context: MangaLoaderContext) :
	MadaraParser(context, MangaParserSource.NITROMANGA, "nitroscans.net", pageSize = 18)
