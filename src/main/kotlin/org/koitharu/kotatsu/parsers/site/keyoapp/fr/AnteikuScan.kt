package org.skepsun.kototoro.parsers.site.keyoapp.fr

import org.skepsun.kototoro.parsers.Broken
import org.skepsun.kototoro.parsers.MangaLoaderContext
import org.skepsun.kototoro.parsers.MangaSourceParser
import org.skepsun.kototoro.parsers.model.MangaParserSource
import org.skepsun.kototoro.parsers.site.keyoapp.KeyoappParser

@Broken
@MangaSourceParser("ANTEIKUSCAN", "AnteikuScan", "fr")
internal class AnteikuScan(context: MangaLoaderContext) :
	KeyoappParser(context, MangaParserSource.ANTEIKUSCAN, "anteikuscan.fr")
