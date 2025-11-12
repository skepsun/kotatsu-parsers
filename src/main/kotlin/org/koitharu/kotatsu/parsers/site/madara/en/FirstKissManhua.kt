package org.skepsun.kototoro.parsers.site.madara.en

import org.skepsun.kototoro.parsers.MangaLoaderContext
import org.skepsun.kototoro.parsers.MangaSourceParser
import org.skepsun.kototoro.parsers.model.MangaParserSource
import org.skepsun.kototoro.parsers.site.madara.MadaraParser

@MangaSourceParser("FIRSTKISSMANHUA", "FirstKissManhua", "en")
internal class FirstKissManhua(context: MangaLoaderContext) :
	MadaraParser(context, MangaParserSource.FIRSTKISSMANHUA, "1stkissmanhua.net", 20) {
	override val listUrl = "manhua/"
}
