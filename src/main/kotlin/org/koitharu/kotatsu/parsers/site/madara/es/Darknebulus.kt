package org.skepsun.kototoro.parsers.site.madara.es

import org.skepsun.kototoro.parsers.MangaLoaderContext
import org.skepsun.kototoro.parsers.MangaSourceParser
import org.skepsun.kototoro.parsers.model.MangaParserSource
import org.skepsun.kototoro.parsers.site.madara.MadaraParser
import org.skepsun.kototoro.parsers.Broken

@Broken("Original site closed")
@MangaSourceParser("DARKNEBULUS", "Darknebulus", "es")
internal class Darknebulus(context: MangaLoaderContext) :
	MadaraParser(context, MangaParserSource.DARKNEBULUS, "www.darknebulus.com")
