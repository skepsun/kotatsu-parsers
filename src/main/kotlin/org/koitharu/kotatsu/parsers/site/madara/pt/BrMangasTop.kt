package org.skepsun.kototoro.parsers.site.madara.pt

import org.skepsun.kototoro.parsers.MangaLoaderContext
import org.skepsun.kototoro.parsers.MangaSourceParser
import org.skepsun.kototoro.parsers.model.MangaParserSource
import org.skepsun.kototoro.parsers.site.madara.MadaraParser

@MangaSourceParser("BRMANGASTOP", "BrMangasTop", "pt")
internal class BrMangasTop(context: MangaLoaderContext) :
	MadaraParser(context, MangaParserSource.BRMANGASTOP, "brmangas.top", 10) {
	override val datePattern: String = "dd 'de' MMMMM 'de' yyyy"
}
