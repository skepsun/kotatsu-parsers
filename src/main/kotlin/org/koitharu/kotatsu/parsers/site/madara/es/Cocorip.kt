package org.skepsun.kototoro.parsers.site.madara.es

import org.skepsun.kototoro.parsers.MangaLoaderContext
import org.skepsun.kototoro.parsers.MangaSourceParser
import org.skepsun.kototoro.parsers.model.MangaParserSource
import org.skepsun.kototoro.parsers.site.madara.MadaraParser
import org.skepsun.kototoro.parsers.Broken

@Broken
@MangaSourceParser("COCORIP", "Cocorip", "es")
internal class Cocorip(context: MangaLoaderContext) :
	MadaraParser(context, MangaParserSource.COCORIP, "cocorip.net", 16) {
	override val datePattern = "dd/MM/yyyy"
}
