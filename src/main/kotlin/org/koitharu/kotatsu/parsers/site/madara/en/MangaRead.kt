package org.skepsun.kototoro.parsers.site.madara.en

import org.skepsun.kototoro.parsers.MangaLoaderContext
import org.skepsun.kototoro.parsers.MangaSourceParser
import org.skepsun.kototoro.parsers.model.MangaParserSource
import org.skepsun.kototoro.parsers.site.madara.MadaraParser

@MangaSourceParser("MANGAREAD", "MangaRead", "en")
internal class MangaRead(context: MangaLoaderContext) :
	MadaraParser(context, MangaParserSource.MANGAREAD, "www.mangaread.org") {
	override val tagPrefix = "genres/"
	override val datePattern = "dd.MM.yyyy"
}
