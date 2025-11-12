package org.skepsun.kototoro.parsers.site.madara.tr

import org.skepsun.kototoro.parsers.MangaLoaderContext
import org.skepsun.kototoro.parsers.MangaSourceParser
import org.skepsun.kototoro.parsers.model.MangaParserSource
import org.skepsun.kototoro.parsers.site.madara.MadaraParser

@MangaSourceParser("TRMANGAOKU", "TrMangaOku", "tr")
internal class TrMangaOku(context: MangaLoaderContext) :
	MadaraParser(context, MangaParserSource.TRMANGAOKU, "trmangaoku.com") {
	override val tagPrefix = "tur/"
}
