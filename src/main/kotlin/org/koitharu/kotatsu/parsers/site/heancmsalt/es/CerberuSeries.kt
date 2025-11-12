package org.skepsun.kototoro.parsers.site.heancmsalt.es

import org.skepsun.kototoro.parsers.MangaLoaderContext
import org.skepsun.kototoro.parsers.MangaSourceParser
import org.skepsun.kototoro.parsers.model.MangaParserSource
import org.skepsun.kototoro.parsers.site.heancmsalt.HeanCmsAlt
import org.skepsun.kototoro.parsers.Broken

@Broken("Not dead, changed template")
@MangaSourceParser("LEGIONSCANS", "CerberusSeries", "es")
internal class CerberuSeries(context: MangaLoaderContext) :
	HeanCmsAlt(context, MangaParserSource.LEGIONSCANS, "legionscans.com")
