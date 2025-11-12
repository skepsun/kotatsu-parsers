package org.skepsun.kototoro.parsers.site.onemanga.fr

import org.skepsun.kototoro.parsers.Broken
import org.skepsun.kototoro.parsers.MangaLoaderContext
import org.skepsun.kototoro.parsers.MangaSourceParser
import org.skepsun.kototoro.parsers.model.MangaParserSource
import org.skepsun.kototoro.parsers.site.onemanga.OneMangaParser

@Broken
@MangaSourceParser("KAIJUNO8", "KaijuNo8", "fr")
internal class KaijuNo8(context: MangaLoaderContext) :
	OneMangaParser(context, MangaParserSource.KAIJUNO8, "kaijuno8.fr")
