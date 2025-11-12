package org.skepsun.kototoro.parsers.site.madara.en

import org.skepsun.kototoro.parsers.MangaLoaderContext
import org.skepsun.kototoro.parsers.MangaSourceParser
import org.skepsun.kototoro.parsers.model.ContentType
import org.skepsun.kototoro.parsers.model.MangaParserSource
import org.skepsun.kototoro.parsers.site.madara.MadaraParser

@MangaSourceParser("THEBLANK", "TheBlank", "en", ContentType.HENTAI)
internal class TheBlank(context: MangaLoaderContext) :
	MadaraParser(context, MangaParserSource.THEBLANK, "theblank.net") {
	override val datePattern = "dd/MM/yyyy"
}
