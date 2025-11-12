package org.skepsun.kototoro.parsers.site.madara.tr

import org.skepsun.kototoro.parsers.Broken
import org.skepsun.kototoro.parsers.MangaLoaderContext
import org.skepsun.kototoro.parsers.MangaSourceParser
import org.skepsun.kototoro.parsers.model.MangaParserSource
import org.skepsun.kototoro.parsers.site.madara.MadaraParser

@Broken
@MangaSourceParser("LICHSUBS", "LichSubs", "tr")
internal class LichSubs(context: MangaLoaderContext) :
	MadaraParser(context, MangaParserSource.LICHSUBS, "www.kuroimanga.com") {
	override val datePattern = "dd/MM/yyyy"
}
