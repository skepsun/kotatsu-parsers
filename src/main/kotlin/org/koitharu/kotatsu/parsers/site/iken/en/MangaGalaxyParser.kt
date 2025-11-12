package org.skepsun.kototoro.parsers.site.iken.en

import org.skepsun.kototoro.parsers.Broken
import org.skepsun.kototoro.parsers.MangaLoaderContext
import org.skepsun.kototoro.parsers.MangaSourceParser
import org.skepsun.kototoro.parsers.model.MangaParserSource
import org.skepsun.kototoro.parsers.site.iken.IkenParser

@Broken("Redirect to VortexScans")
@MangaSourceParser("MANGAGALAXY", "MangaGalaxy", "en")
internal class MangaGalaxyParser(context: MangaLoaderContext) :
	IkenParser(context, MangaParserSource.MANGAGALAXY, "vortexscans.org", 18)
