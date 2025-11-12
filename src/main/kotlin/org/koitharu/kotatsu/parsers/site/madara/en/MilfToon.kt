package org.skepsun.kototoro.parsers.site.madara.en

import org.skepsun.kototoro.parsers.MangaLoaderContext
import org.skepsun.kototoro.parsers.MangaSourceParser
import org.skepsun.kototoro.parsers.model.ContentType
import org.skepsun.kototoro.parsers.model.MangaParserSource
import org.skepsun.kototoro.parsers.site.madara.MadaraParser

@MangaSourceParser("MILFTOON", "MilfToon", "en", ContentType.HENTAI)
internal class MilfToon(context: MangaLoaderContext) :
	MadaraParser(context, MangaParserSource.MILFTOON, "milftoon.xxx", 20) {
	override val postReq = true
	override val datePattern = "d MMMM, yyyy"
}
