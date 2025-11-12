package org.skepsun.kototoro.parsers.site.madara.pt

import org.skepsun.kototoro.parsers.MangaLoaderContext
import org.skepsun.kototoro.parsers.MangaSourceParser
import org.skepsun.kototoro.parsers.model.ContentType
import org.skepsun.kototoro.parsers.model.MangaParserSource
import org.skepsun.kototoro.parsers.site.madara.MadaraParser

@MangaSourceParser("NOCSUMMER", "NocturneSummer", "pt", ContentType.HENTAI)
internal class Nocsummer(context: MangaLoaderContext) :
	MadaraParser(context, MangaParserSource.NOCSUMMER, "nocfsb.com", 18) {
	override val datePattern = "dd 'de' MMMMM 'de' yyyy"
}
