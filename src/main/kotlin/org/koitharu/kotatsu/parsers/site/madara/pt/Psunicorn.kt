package org.skepsun.kototoro.parsers.site.madara.pt

import org.skepsun.kototoro.parsers.Broken
import org.skepsun.kototoro.parsers.MangaLoaderContext
import org.skepsun.kototoro.parsers.MangaSourceParser
import org.skepsun.kototoro.parsers.model.ContentType
import org.skepsun.kototoro.parsers.model.MangaParserSource
import org.skepsun.kototoro.parsers.site.madara.MadaraParser

@Broken
@MangaSourceParser("PSUNICORN", "PsUnicorn", "pt", ContentType.HENTAI)
internal class Psunicorn(context: MangaLoaderContext) :
	MadaraParser(context, MangaParserSource.PSUNICORN, "psunicorn.com")
