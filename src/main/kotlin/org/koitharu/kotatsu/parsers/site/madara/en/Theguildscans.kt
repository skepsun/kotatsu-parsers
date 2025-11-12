package org.skepsun.kototoro.parsers.site.madara.en

import org.skepsun.kototoro.parsers.Broken
import org.skepsun.kototoro.parsers.MangaLoaderContext
import org.skepsun.kototoro.parsers.MangaSourceParser
import org.skepsun.kototoro.parsers.model.MangaParserSource
import org.skepsun.kototoro.parsers.site.madara.MadaraParser

@Broken
@MangaSourceParser("THEGUILDSCANS", "TheGuildScans", "en")
internal class Theguildscans(context: MangaLoaderContext) :
	MadaraParser(context, MangaParserSource.THEGUILDSCANS, "theguildscans.com") {
	override val postReq = true
}
