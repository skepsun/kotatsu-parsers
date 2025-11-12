package org.skepsun.kototoro.parsers.site.zeistmanga.ar

import org.skepsun.kototoro.parsers.MangaLoaderContext
import org.skepsun.kototoro.parsers.MangaSourceParser
import org.skepsun.kototoro.parsers.model.MangaParserSource
import org.skepsun.kototoro.parsers.site.zeistmanga.ZeistMangaParser

@MangaSourceParser("MANHATOK", "ManhaTok", "ar")
internal class ManhaTok(context: MangaLoaderContext) :
	ZeistMangaParser(context, MangaParserSource.MANHATOK, "manhatok.blogspot.com") {
	override val selectPage = "#seoneurons-target img"
}
