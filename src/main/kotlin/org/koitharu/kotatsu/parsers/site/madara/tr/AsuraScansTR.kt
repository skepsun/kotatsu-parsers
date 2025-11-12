package org.skepsun.kototoro.parsers.site.madara.tr

import org.skepsun.kototoro.parsers.MangaLoaderContext
import org.skepsun.kototoro.parsers.MangaSourceParser
import org.skepsun.kototoro.parsers.model.MangaParserSource
import org.skepsun.kototoro.parsers.site.madara.MadaraParser

@MangaSourceParser("ASURASCANSTR", "AsuraScansTR", "tr")
internal class AsuraScansTR(context: MangaLoaderContext) :
	MadaraParser(context, MangaParserSource.ASURASCANSTR, "asurascans.com.tr") {
	override val tagPrefix = "tur/"
}
