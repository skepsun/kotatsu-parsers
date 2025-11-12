package org.skepsun.kototoro.parsers.site.madara.en

import org.skepsun.kototoro.parsers.MangaLoaderContext
import org.skepsun.kototoro.parsers.MangaSourceParser
import org.skepsun.kototoro.parsers.model.MangaParserSource
import org.skepsun.kototoro.parsers.site.madara.MadaraParser

@MangaSourceParser("PLATINUMSCANS", "PlatinumScans", "en")
internal class PlatinumScans(context: MangaLoaderContext) :
	MadaraParser(context, MangaParserSource.PLATINUMSCANS, "platinumscans.com", pageSize = 10) {
	override val postReq = true
}
