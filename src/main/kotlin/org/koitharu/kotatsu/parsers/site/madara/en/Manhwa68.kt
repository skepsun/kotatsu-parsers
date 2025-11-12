package org.skepsun.kototoro.parsers.site.madara.en

import org.skepsun.kototoro.parsers.MangaLoaderContext
import org.skepsun.kototoro.parsers.MangaSourceParser
import org.skepsun.kototoro.parsers.model.ContentType
import org.skepsun.kototoro.parsers.model.MangaParserSource
import org.skepsun.kototoro.parsers.site.madara.MadaraParser

@MangaSourceParser("MANHWA68", "Manhwa68", "en", ContentType.HENTAI)
internal class Manhwa68(context: MangaLoaderContext) :
	MadaraParser(context, MangaParserSource.MANHWA68, "manhwa68.com")
