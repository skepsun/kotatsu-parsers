package org.skepsun.kototoro.parsers.site.madara.id

import org.skepsun.kototoro.parsers.Broken
import org.skepsun.kototoro.parsers.MangaLoaderContext
import org.skepsun.kototoro.parsers.MangaSourceParser
import org.skepsun.kototoro.parsers.model.MangaParserSource
import org.skepsun.kototoro.parsers.site.madara.MadaraParser

@Broken
@MangaSourceParser("HWAGO", "Hwago", "id")
internal class Hwago(context: MangaLoaderContext) :
	MadaraParser(context, MangaParserSource.HWAGO, "hwago01.xyz") {
	override val datePattern = "d MMMM yyyy"
}
