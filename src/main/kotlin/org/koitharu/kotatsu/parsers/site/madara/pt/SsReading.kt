package org.skepsun.kototoro.parsers.site.madara.pt

import org.skepsun.kototoro.parsers.MangaLoaderContext
import org.skepsun.kototoro.parsers.MangaSourceParser
import org.skepsun.kototoro.parsers.model.MangaParserSource
import org.skepsun.kototoro.parsers.site.madara.MadaraParser

@MangaSourceParser("SSREADING", "SsReading", "pt")
internal class SsReading(context: MangaLoaderContext) :
	MadaraParser(context, MangaParserSource.SSREADING, "ssreading.com.br") {
	override val datePattern: String = "dd 'de' MMM 'de' yyyy"
}
