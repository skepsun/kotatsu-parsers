package org.skepsun.kototoro.parsers.site.madara.pt

import org.skepsun.kototoro.parsers.Broken
import org.skepsun.kototoro.parsers.MangaLoaderContext
import org.skepsun.kototoro.parsers.MangaSourceParser
import org.skepsun.kototoro.parsers.model.MangaParserSource
import org.skepsun.kototoro.parsers.site.madara.MadaraParser

@Broken
@MangaSourceParser("FOXWHITE", "FoxWhite", "pt")
internal class FoxWhite(context: MangaLoaderContext) :
	MadaraParser(context, MangaParserSource.FOXWHITE, "foxwhite.com.br")
