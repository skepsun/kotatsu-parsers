package org.skepsun.kototoro.parsers.site.madara.tr

import org.skepsun.kototoro.parsers.MangaLoaderContext
import org.skepsun.kototoro.parsers.MangaSourceParser
import org.skepsun.kototoro.parsers.model.MangaParserSource
import org.skepsun.kototoro.parsers.site.madara.MadaraParser
import org.skepsun.kototoro.parsers.Broken

@Broken
@MangaSourceParser("RAGNARSCANS", "Ragnarscans", "tr")
internal class Ragnarscans(context: MangaLoaderContext) :
    MadaraParser(context, MangaParserSource.RAGNARSCANS, "ragnarscans.com", pageSize = 10) {
	  override val datePattern = "d MMMM yyyy"
}
