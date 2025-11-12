package org.skepsun.kototoro.parsers.site.iken.en

import org.skepsun.kototoro.parsers.MangaLoaderContext
import org.skepsun.kototoro.parsers.MangaSourceParser
import org.skepsun.kototoro.parsers.model.MangaParserSource
import org.skepsun.kototoro.parsers.site.iken.IkenParser

@MangaSourceParser("VORTEXSCANS", "VortexScans", "en")
internal class VortexScans(context: MangaLoaderContext) :
	IkenParser(context, MangaParserSource.VORTEXSCANS, "vortexscans.org", 18, true)
