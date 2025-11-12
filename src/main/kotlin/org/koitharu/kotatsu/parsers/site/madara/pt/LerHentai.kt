package org.skepsun.kototoro.parsers.site.madara.pt

import org.skepsun.kototoro.parsers.MangaLoaderContext
import org.skepsun.kototoro.parsers.MangaSourceParser
import org.skepsun.kototoro.parsers.model.ContentType
import org.skepsun.kototoro.parsers.model.MangaParserSource
import org.skepsun.kototoro.parsers.site.madara.MadaraParser

@MangaSourceParser("LERHENTAI", "LerHentai", "pt", ContentType.HENTAI)
internal class LerHentai(context: MangaLoaderContext) :
	MadaraParser(context, MangaParserSource.LERHENTAI, "lerhentai.com", 20) {
	override val datePattern: String = "dd 'de' MMMMM 'de' yyyy"
}
