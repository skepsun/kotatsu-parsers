package org.skepsun.kototoro.parsers.site.madara.pt

import org.skepsun.kototoro.parsers.MangaLoaderContext
import org.skepsun.kototoro.parsers.MangaSourceParser
import org.skepsun.kototoro.parsers.model.MangaParserSource
import org.skepsun.kototoro.parsers.site.madara.MadaraParser

@MangaSourceParser("FENIXPROJECT", "FenixProject", "pt")
internal class FenixProject(context: MangaLoaderContext) :
	MadaraParser(context, MangaParserSource.FENIXPROJECT, "fenixproject.site", 10) {
	override val datePattern: String = "dd/MM/yyyy"
}
