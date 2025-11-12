package org.skepsun.kototoro.parsers.site.keyoapp.en

import org.skepsun.kototoro.parsers.MangaLoaderContext
import org.skepsun.kototoro.parsers.MangaSourceParser
import org.skepsun.kototoro.parsers.model.MangaParserSource
import org.skepsun.kototoro.parsers.site.keyoapp.KeyoappParser

@MangaSourceParser("NECROSCANS", "NecroScans", "en")
internal class NecroScans(context: MangaLoaderContext) :
	KeyoappParser(context, MangaParserSource.NECROSCANS, "necroscans.com")
