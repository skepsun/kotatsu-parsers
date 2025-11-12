package org.skepsun.kototoro.parsers.site.keyoapp.en

import org.skepsun.kototoro.parsers.MangaLoaderContext
import org.skepsun.kototoro.parsers.MangaSourceParser
import org.skepsun.kototoro.parsers.model.MangaParserSource
import org.skepsun.kototoro.parsers.site.keyoapp.KeyoappParser

@MangaSourceParser("RAISCANS", "KenScans", "en")
internal class RaiScans(context: MangaLoaderContext) :
	KeyoappParser(context, MangaParserSource.RAISCANS, "kenscans.com")
