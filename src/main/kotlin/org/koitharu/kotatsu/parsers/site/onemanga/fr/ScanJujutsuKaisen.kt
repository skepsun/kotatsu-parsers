package org.skepsun.kototoro.parsers.site.onemanga.fr

import org.skepsun.kototoro.parsers.MangaLoaderContext
import org.skepsun.kototoro.parsers.MangaSourceParser
import org.skepsun.kototoro.parsers.model.MangaParserSource
import org.skepsun.kototoro.parsers.site.onemanga.OneMangaParser

@MangaSourceParser("SCANJUJUTSUKAISEN", "ScanJujutsuKaisen", "fr")
internal class ScanJujutsuKaisen(context: MangaLoaderContext) :
	OneMangaParser(context, MangaParserSource.SCANJUJUTSUKAISEN, "scanjujutsukaisen.com")
