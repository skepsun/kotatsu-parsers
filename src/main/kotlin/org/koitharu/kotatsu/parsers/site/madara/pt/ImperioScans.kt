package org.skepsun.kototoro.parsers.site.madara.pt

import org.skepsun.kototoro.parsers.Broken
import org.skepsun.kototoro.parsers.MangaLoaderContext
import org.skepsun.kototoro.parsers.MangaSourceParser
import org.skepsun.kototoro.parsers.model.MangaParserSource
import org.skepsun.kototoro.parsers.site.madara.MadaraParser

@Broken
@MangaSourceParser("IMPERIOSCANS", "ImperioScans", "pt")
internal class ImperioScans(context: MangaLoaderContext) :
	MadaraParser(context, MangaParserSource.IMPERIOSCANS, "imperioscans.com.br") {
	override val datePattern: String = "dd/MM/yyyy"
}
