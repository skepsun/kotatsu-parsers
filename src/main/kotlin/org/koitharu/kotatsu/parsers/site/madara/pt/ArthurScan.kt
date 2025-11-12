package org.skepsun.kototoro.parsers.site.madara.pt

import org.skepsun.kototoro.parsers.MangaLoaderContext
import org.skepsun.kototoro.parsers.MangaSourceParser
import org.skepsun.kototoro.parsers.model.MangaParserSource
import org.skepsun.kototoro.parsers.site.madara.MadaraParser

@MangaSourceParser("ARTHUR_SCAN", "ArthurScan", "pt")
internal class ArthurScan(context: MangaLoaderContext) :
	MadaraParser(context, MangaParserSource.ARTHUR_SCAN, "arthurscan.xyz")
