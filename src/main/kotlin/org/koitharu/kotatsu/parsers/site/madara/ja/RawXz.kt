package org.skepsun.kototoro.parsers.site.madara.ja

import org.skepsun.kototoro.parsers.MangaLoaderContext
import org.skepsun.kototoro.parsers.MangaSourceParser
import org.skepsun.kototoro.parsers.model.MangaParserSource
import org.skepsun.kototoro.parsers.site.madara.MadaraParser
import org.skepsun.kototoro.parsers.Broken

@Broken
@MangaSourceParser("RAWXZ", "RawXz", "ja")
internal class RawXz(context: MangaLoaderContext) :
	MadaraParser(context, MangaParserSource.RAWXZ, "rawxz.ac") {
	override val listUrl = "jp-manga/"
}
