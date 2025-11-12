package org.skepsun.kototoro.parsers.site.madara.pt

import org.skepsun.kototoro.parsers.MangaLoaderContext
import org.skepsun.kototoro.parsers.MangaSourceParser
import org.skepsun.kototoro.parsers.model.MangaParserSource
import org.skepsun.kototoro.parsers.site.madara.MadaraParser

@MangaSourceParser("LERMANGAS", "Lermangas", "pt")
internal class Lermangas(context: MangaLoaderContext) :
	MadaraParser(context, MangaParserSource.LERMANGAS, "lermangas.me", pageSize = 20) {
	override val datePattern = "dd 'de' MMMMM 'de' yyyy"
}
