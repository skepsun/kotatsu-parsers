package org.skepsun.kototoro.parsers.site.madara.tr

import org.skepsun.kototoro.parsers.Broken
import org.skepsun.kototoro.parsers.MangaLoaderContext
import org.skepsun.kototoro.parsers.MangaSourceParser
import org.skepsun.kototoro.parsers.model.MangaParserSource
import org.skepsun.kototoro.parsers.site.madara.MadaraParser

@Broken
@MangaSourceParser("YETISKINRUYAMANGA", "Yetişkin Rüya Manga", "tr")
internal class YetiskinRuyaManga(context: MangaLoaderContext) :
	MadaraParser(context, MangaParserSource.YETISKINRUYAMANGA, "www.yetiskinruyamanga.com", 16) {
	override val datePattern = "dD/MM/yyyy"
}
