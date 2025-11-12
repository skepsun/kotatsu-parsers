package org.skepsun.kototoro.parsers.site.madara.tr

import org.skepsun.kototoro.parsers.MangaLoaderContext
import org.skepsun.kototoro.parsers.MangaSourceParser
import org.skepsun.kototoro.parsers.model.MangaParserSource
import org.skepsun.kototoro.parsers.site.madara.MadaraParser

@MangaSourceParser("MANGATILKISI", "MangaTilkisi", "tr")
internal class MangaTilkisi(context: MangaLoaderContext) :
	MadaraParser(context, MangaParserSource.MANGATILKISI, "www.mangatilkisi.net", pageSize = 18) {
	override val datePattern = "dd/MM/yyyy"
  }
