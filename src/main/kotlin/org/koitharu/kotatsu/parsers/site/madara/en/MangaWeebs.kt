package org.skepsun.kototoro.parsers.site.madara.en

import org.skepsun.kototoro.parsers.MangaLoaderContext
import org.skepsun.kototoro.parsers.MangaSourceParser
import org.skepsun.kototoro.parsers.model.MangaParserSource
import org.skepsun.kototoro.parsers.site.madara.MadaraParser

@MangaSourceParser("MANGAWEEBS", "MangaWeebs", "en")
internal class MangaWeebs(context: MangaLoaderContext) :
	MadaraParser(context, MangaParserSource.MANGAWEEBS, "mangaweebs.in", pageSize = 20) {
	override val datePattern = "dd MMMM HH:mm"
}
