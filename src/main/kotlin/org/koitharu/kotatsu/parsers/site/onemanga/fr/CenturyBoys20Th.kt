package org.skepsun.kototoro.parsers.site.onemanga.fr

import org.skepsun.kototoro.parsers.MangaLoaderContext
import org.skepsun.kototoro.parsers.MangaSourceParser
import org.skepsun.kototoro.parsers.model.MangaParserSource
import org.skepsun.kototoro.parsers.site.onemanga.OneMangaParser
import org.skepsun.kototoro.parsers.Broken

@Broken("Original site closed")
@MangaSourceParser("CENTURYBOYS20TH", "20ThCenturyBoys", "fr")
internal class CenturyBoys20Th(context: MangaLoaderContext) :
	OneMangaParser(context, MangaParserSource.CENTURYBOYS20TH, "20thcenturyboys.fr")
