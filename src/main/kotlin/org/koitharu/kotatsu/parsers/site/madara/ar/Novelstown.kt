package org.skepsun.kototoro.parsers.site.madara.ar

import org.skepsun.kototoro.parsers.Broken
import org.skepsun.kototoro.parsers.MangaLoaderContext
import org.skepsun.kototoro.parsers.MangaSourceParser
import org.skepsun.kototoro.parsers.model.MangaParserSource
import org.skepsun.kototoro.parsers.site.madara.MadaraParser

@Broken
@MangaSourceParser("NOVELSTOWN", "NovelsTown", "ar")
internal class Novelstown(context: MangaLoaderContext) :
	MadaraParser(context, MangaParserSource.NOVELSTOWN, "novelstown.com") {
	override val datePattern = "d MMMM، yyyy"
}
