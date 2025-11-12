package org.skepsun.kototoro.parsers.site.madara.pt

import org.skepsun.kototoro.parsers.MangaLoaderContext
import org.skepsun.kototoro.parsers.MangaSourceParser
import org.skepsun.kototoro.parsers.model.MangaParserSource
import org.skepsun.kototoro.parsers.site.madara.MadaraParser

@MangaSourceParser("DIANXIATRADS", "DianxiaTrads", "pt")
internal class DianxiaTrads(context: MangaLoaderContext) :
	MadaraParser(context, MangaParserSource.DIANXIATRADS, "dianxiatrads.com", 10) {
	override val datePattern: String = "dd/MM/yyyy"
}
