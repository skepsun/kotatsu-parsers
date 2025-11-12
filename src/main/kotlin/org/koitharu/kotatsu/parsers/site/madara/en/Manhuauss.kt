package org.skepsun.kototoro.parsers.site.madara.en

import org.skepsun.kototoro.parsers.MangaLoaderContext
import org.skepsun.kototoro.parsers.MangaSourceParser
import org.skepsun.kototoro.parsers.model.MangaParserSource
import org.skepsun.kototoro.parsers.site.madara.MadaraParser

@MangaSourceParser("MANHUAUSS", "Manhuauss", "en")
internal class Manhuauss(context: MangaLoaderContext) :
	MadaraParser(context, MangaParserSource.MANHUAUSS, "manhuauss.com") {
	override val withoutAjax = true
}
