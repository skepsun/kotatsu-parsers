package org.skepsun.kototoro.parsers.site.madara.tr

import org.skepsun.kototoro.parsers.Broken
import org.skepsun.kototoro.parsers.MangaLoaderContext
import org.skepsun.kototoro.parsers.MangaSourceParser
import org.skepsun.kototoro.parsers.model.MangaParserSource
import org.skepsun.kototoro.parsers.site.madara.MadaraParser

@Broken("Content not found or removed")
@MangaSourceParser("MILASUB", "MilaSub", "tr")
internal class MilaSub(context: MangaLoaderContext) :
	MadaraParser(context, MangaParserSource.MILASUB, "milascans.tr", 20) {
	override val datePattern = "d MMMM yyyy"
}
