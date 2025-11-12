package org.skepsun.kototoro.parsers.site.madara.pt

import org.skepsun.kototoro.parsers.Broken
import org.skepsun.kototoro.parsers.MangaLoaderContext
import org.skepsun.kototoro.parsers.MangaSourceParser
import org.skepsun.kototoro.parsers.model.MangaParserSource
import org.skepsun.kototoro.parsers.site.madara.MadaraParser

@Broken
@MangaSourceParser("MOMONOHANASCAN", "MomonohanaScan", "pt")
internal class MomonohanaScan(context: MangaLoaderContext) :
	MadaraParser(context, MangaParserSource.MOMONOHANASCAN, "momonohanascan.com", 10) {
	override val datePattern: String = "dd/MM/yyyy"
}
