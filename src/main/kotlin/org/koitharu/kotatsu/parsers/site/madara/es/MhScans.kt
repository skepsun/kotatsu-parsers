package org.skepsun.kototoro.parsers.site.madara.es

import org.skepsun.kototoro.parsers.MangaLoaderContext
import org.skepsun.kototoro.parsers.MangaSourceParser
import org.skepsun.kototoro.parsers.model.MangaParserSource
import org.skepsun.kototoro.parsers.site.madara.MadaraParser

@MangaSourceParser("MHSCANS", "MhScans", "es")
internal class MhScans(context: MangaLoaderContext) :
	MadaraParser(context, MangaParserSource.MHSCANS, "mhscans.mundoalterno.org") {
	override val datePattern = "d 'de' MMMMM 'de' yyyy"
	override val listUrl = "series/"
}
