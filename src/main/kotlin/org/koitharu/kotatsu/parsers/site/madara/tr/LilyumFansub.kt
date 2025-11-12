package org.skepsun.kototoro.parsers.site.madara.tr

import org.skepsun.kototoro.parsers.MangaLoaderContext
import org.skepsun.kototoro.parsers.MangaSourceParser
import org.skepsun.kototoro.parsers.model.MangaParserSource
import org.skepsun.kototoro.parsers.site.madara.MadaraParser

@MangaSourceParser("LILYUMFANSUB", "LilyumFansub", "tr")
internal class LilyumFansub(context: MangaLoaderContext) :
	MadaraParser(context, MangaParserSource.LILYUMFANSUB, "lilyumfansub.com.tr", 16)
