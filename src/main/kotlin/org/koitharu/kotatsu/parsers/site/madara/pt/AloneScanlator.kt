package org.skepsun.kototoro.parsers.site.madara.pt

import org.skepsun.kototoro.parsers.MangaLoaderContext
import org.skepsun.kototoro.parsers.MangaSourceParser
import org.skepsun.kototoro.parsers.model.MangaParserSource
import org.skepsun.kototoro.parsers.site.madara.MadaraParser

@MangaSourceParser("ALONESCANLATOR", "AloneScanlator", "pt")
internal class AloneScanlator(context: MangaLoaderContext) :
	MadaraParser(context, MangaParserSource.ALONESCANLATOR, "alonescanlator.com.br", 10) {
	override val datePattern: String = "dd/MM/yyyy"
}
