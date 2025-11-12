package org.skepsun.kototoro.parsers.site.keyoapp.en

import org.skepsun.kototoro.parsers.MangaLoaderContext
import org.skepsun.kototoro.parsers.MangaSourceParser
import org.skepsun.kototoro.parsers.model.MangaParserSource
import org.skepsun.kototoro.parsers.site.keyoapp.KeyoappParser

@MangaSourceParser("ARVENSCANS", "ArvenComics", "en")
internal class ArvenScans(context: MangaLoaderContext) :
	KeyoappParser(context, MangaParserSource.ARVENSCANS, "arvencomics.com")
