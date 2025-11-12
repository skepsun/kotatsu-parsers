package org.skepsun.kototoro.parsers.site.onemanga.fr

import org.skepsun.kototoro.parsers.MangaLoaderContext
import org.skepsun.kototoro.parsers.MangaSourceParser
import org.skepsun.kototoro.parsers.model.MangaParserSource
import org.skepsun.kototoro.parsers.site.onemanga.OneMangaParser

@MangaSourceParser("ONEPIECESCAN", "OnePieceScan", "fr")
internal class OnePieceScan(context: MangaLoaderContext) :
	OneMangaParser(context, MangaParserSource.ONEPIECESCAN, "onepiecescan.fr")
