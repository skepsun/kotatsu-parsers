package org.skepsun.kototoro.parsers.site.madara.ar

import org.skepsun.kototoro.parsers.MangaLoaderContext
import org.skepsun.kototoro.parsers.MangaSourceParser
import org.skepsun.kototoro.parsers.model.MangaParserSource
import org.skepsun.kototoro.parsers.site.madara.MadaraParser

@MangaSourceParser("CROWSCANS", "Hadess", "ar")
internal class CrowScans(context: MangaLoaderContext) :
	MadaraParser(context, MangaParserSource.CROWSCANS, "www.hadess.xyz") {
	override val datePattern = "dd MMMM، yyyy"
}
