package org.skepsun.kototoro.parsers.site.madara.ar

import org.skepsun.kototoro.parsers.MangaLoaderContext
import org.skepsun.kototoro.parsers.MangaSourceParser
import org.skepsun.kototoro.parsers.model.MangaParserSource
import org.skepsun.kototoro.parsers.site.madara.MadaraParser

@MangaSourceParser("MANGALIONZ", "Manga-Lionz", "ar")
internal class MangaLionz(context: MangaLoaderContext) :
	MadaraParser(context, MangaParserSource.MANGALIONZ, "manga-lionz.com", pageSize = 10)
