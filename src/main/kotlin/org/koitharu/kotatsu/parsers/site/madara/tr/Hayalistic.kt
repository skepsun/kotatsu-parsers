package org.skepsun.kototoro.parsers.site.madara.tr

import org.skepsun.kototoro.parsers.MangaLoaderContext
import org.skepsun.kototoro.parsers.MangaSourceParser
import org.skepsun.kototoro.parsers.model.MangaParserSource
import org.skepsun.kototoro.parsers.site.madara.MadaraParser

@MangaSourceParser("HAYALISTIC", "Hayalistic", "tr")
internal class Hayalistic(context: MangaLoaderContext) :
	MadaraParser(context, MangaParserSource.HAYALISTIC, "hayalistic.com.tr", 24) {
	override val datePattern = "dd/MM/yyyy"
}
