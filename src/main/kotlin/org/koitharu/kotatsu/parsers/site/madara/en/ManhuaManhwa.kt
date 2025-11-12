package org.skepsun.kototoro.parsers.site.madara.en

import org.skepsun.kototoro.parsers.MangaLoaderContext
import org.skepsun.kototoro.parsers.MangaSourceParser
import org.skepsun.kototoro.parsers.model.MangaParserSource
import org.skepsun.kototoro.parsers.site.madara.MadaraParser

@MangaSourceParser("MANHUAMANHWA", "ManhuaManhwa", "en")
internal class ManhuaManhwa(context: MangaLoaderContext) :
	MadaraParser(context, MangaParserSource.MANHUAMANHWA, "manhuamanhwa.com") {
	override val datePattern = "MM/dd/yyyy"
}
