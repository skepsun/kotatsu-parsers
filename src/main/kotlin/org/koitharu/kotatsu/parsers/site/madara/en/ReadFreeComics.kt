package org.skepsun.kototoro.parsers.site.madara.en

import org.skepsun.kototoro.parsers.MangaLoaderContext
import org.skepsun.kototoro.parsers.MangaSourceParser
import org.skepsun.kototoro.parsers.model.MangaParserSource
import org.skepsun.kototoro.parsers.site.madara.MadaraParser
import org.skepsun.kototoro.parsers.Broken

@Broken
@MangaSourceParser("READFREECOMICS", "ReadFreeComics", "en")
internal class ReadFreeComics(context: MangaLoaderContext) :
	MadaraParser(context, MangaParserSource.READFREECOMICS, "readfreecomics.com") {
	override val tagPrefix = "webtoon-comic-genre/"
	override val listUrl = "webtoon-comic/"
}
