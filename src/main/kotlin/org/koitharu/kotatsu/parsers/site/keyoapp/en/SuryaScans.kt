package org.skepsun.kototoro.parsers.site.keyoapp.en

import org.skepsun.kototoro.parsers.MangaLoaderContext
import org.skepsun.kototoro.parsers.MangaSourceParser
import org.skepsun.kototoro.parsers.model.MangaParserSource
import org.skepsun.kototoro.parsers.site.keyoapp.KeyoappParser

@MangaSourceParser("SURYASCANS", "GenzToon", "en")
internal class SuryaScans(context: MangaLoaderContext) :
	KeyoappParser(context, MangaParserSource.SURYASCANS, "genzupdates.com")
