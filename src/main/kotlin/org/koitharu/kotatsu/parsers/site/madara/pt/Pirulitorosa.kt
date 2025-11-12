package org.skepsun.kototoro.parsers.site.madara.pt

import org.skepsun.kototoro.parsers.MangaLoaderContext
import org.skepsun.kototoro.parsers.MangaSourceParser
import org.skepsun.kototoro.parsers.model.ContentType
import org.skepsun.kototoro.parsers.model.MangaParserSource
import org.skepsun.kototoro.parsers.site.madara.MadaraParser

@MangaSourceParser("PIRULITOROSA", "PirulitoRosa", "pt", ContentType.HENTAI)
internal class Pirulitorosa(context: MangaLoaderContext) :
	MadaraParser(context, MangaParserSource.PIRULITOROSA, "pirulitorosa.site") {
	override val postReq = true
	override val datePattern: String = "dd/MM/yyyy"
}
