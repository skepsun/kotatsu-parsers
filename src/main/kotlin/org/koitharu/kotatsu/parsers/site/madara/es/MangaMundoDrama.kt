package org.skepsun.kototoro.parsers.site.madara.es

import org.skepsun.kototoro.parsers.MangaLoaderContext
import org.skepsun.kototoro.parsers.MangaSourceParser
import org.skepsun.kototoro.parsers.model.MangaParserSource
import org.skepsun.kototoro.parsers.site.madara.MadaraParser

@MangaSourceParser("MANGAMUNDODRAMA", "InmortalScan", "es")
internal class MangaMundoDrama(context: MangaLoaderContext) :
	MadaraParser(context, MangaParserSource.MANGAMUNDODRAMA, "scaninmortal.com") {
	override val listUrl = "mg/"
}
