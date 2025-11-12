package org.skepsun.kototoro.parsers.site.madara.es

import org.skepsun.kototoro.parsers.MangaLoaderContext
import org.skepsun.kototoro.parsers.MangaSourceParser
import org.skepsun.kototoro.parsers.model.MangaParserSource
import org.skepsun.kototoro.parsers.site.madara.MadaraParser

@MangaSourceParser("HADESNOFANSUB", "HadesNoFansub", "es")
internal class HadesNoFansub(context: MangaLoaderContext) :
	MadaraParser(context, MangaParserSource.HADESNOFANSUB, "hadesnofansub.com", 10) {
	override val datePattern: String = "MM/dd/yyyy"
}
