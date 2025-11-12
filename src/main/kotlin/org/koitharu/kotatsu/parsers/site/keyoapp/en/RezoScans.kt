package org.skepsun.kototoro.parsers.site.keyoapp.en

import org.skepsun.kototoro.parsers.MangaLoaderContext
import org.skepsun.kototoro.parsers.MangaSourceParser
import org.skepsun.kototoro.parsers.model.MangaParserSource
import org.skepsun.kototoro.parsers.site.keyoapp.KeyoappParser

@MangaSourceParser("REZOSCANS", "RezoScans", "en")
internal class RezoScans(context: MangaLoaderContext) :
	KeyoappParser(context, MangaParserSource.REZOSCANS, "rezoscans.com")
