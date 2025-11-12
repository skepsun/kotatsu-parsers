package org.skepsun.kototoro.parsers.site.madara.pt

import org.skepsun.kototoro.parsers.MangaLoaderContext
import org.skepsun.kototoro.parsers.MangaSourceParser
import org.skepsun.kototoro.parsers.model.ContentType
import org.skepsun.kototoro.parsers.model.MangaParserSource
import org.skepsun.kototoro.parsers.site.madara.MadaraParser
import org.skepsun.kototoro.parsers.Broken

@Broken("Original site closed")
@MangaSourceParser("GOOFFANSUB", "GoofFansub", "pt", ContentType.HENTAI)
internal class GoofFansub(context: MangaLoaderContext) :
	MadaraParser(context, MangaParserSource.GOOFFANSUB, "gooffansub.com") {
	override val datePattern: String = "dd/MM/yyyy"
}
