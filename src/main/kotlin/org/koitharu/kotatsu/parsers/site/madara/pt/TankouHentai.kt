package org.skepsun.kototoro.parsers.site.madara.pt

import org.skepsun.kototoro.parsers.MangaLoaderContext
import org.skepsun.kototoro.parsers.MangaSourceParser
import org.skepsun.kototoro.parsers.model.ContentType
import org.skepsun.kototoro.parsers.model.MangaParserSource
import org.skepsun.kototoro.parsers.site.madara.MadaraParser

@MangaSourceParser("TANKOUHENTAI", "TankouHentai", "pt", ContentType.HENTAI)
internal class TankouHentai(context: MangaLoaderContext) :
	MadaraParser(context, MangaParserSource.TANKOUHENTAI, "tankouhentai.com", pageSize = 16) {
	override val datePattern: String = "dd 'de' MMMMM 'de' yyyy"
}
