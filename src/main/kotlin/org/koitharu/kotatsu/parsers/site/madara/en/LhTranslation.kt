package org.skepsun.kototoro.parsers.site.madara.en

import org.skepsun.kototoro.parsers.MangaLoaderContext
import org.skepsun.kototoro.parsers.MangaSourceParser
import org.skepsun.kototoro.parsers.model.MangaParserSource
import org.skepsun.kototoro.parsers.site.madara.MadaraParser

@MangaSourceParser("LHTRANSLATION", "LhTranslation", "en")
internal class LhTranslation(context: MangaLoaderContext) :
	MadaraParser(context, MangaParserSource.LHTRANSLATION, "lhtranslation.net")
