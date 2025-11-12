package org.skepsun.kototoro.parsers.site.madara.pt

import org.skepsun.kototoro.parsers.MangaLoaderContext
import org.skepsun.kototoro.parsers.MangaSourceParser
import org.skepsun.kototoro.parsers.model.MangaParserSource
import org.skepsun.kototoro.parsers.site.madara.MadaraParser

@MangaSourceParser("NINJASCAN", "NinjaComics", "pt")
internal class NinjaScan(context: MangaLoaderContext) :
	MadaraParser(context, MangaParserSource.NINJASCAN, "ninjacomics.xyz") {
	override val datePattern: String = "dd 'de' MMMMM 'de' yyyy"
}
