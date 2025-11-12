package org.skepsun.kototoro.parsers.site.madara.en

import org.skepsun.kototoro.parsers.MangaLoaderContext
import org.skepsun.kototoro.parsers.MangaSourceParser
import org.skepsun.kototoro.parsers.model.ContentType
import org.skepsun.kototoro.parsers.model.MangaParserSource
import org.skepsun.kototoro.parsers.site.madara.MadaraParser

// redirect to @MangaZin
@MangaSourceParser("MANGABEE", "MangaBee", "en", ContentType.HENTAI)
internal class MangaBee(context: MangaLoaderContext) :
	MadaraParser(context, MangaParserSource.MANGABEE, "mangazin.org") {
	override val datePattern = "MM/dd/yyyy"
}
