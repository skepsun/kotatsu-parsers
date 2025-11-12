package org.skepsun.kototoro.parsers.site.madara.en

import org.skepsun.kototoro.parsers.Broken
import org.skepsun.kototoro.parsers.MangaLoaderContext
import org.skepsun.kototoro.parsers.MangaSourceParser
import org.skepsun.kototoro.parsers.model.MangaParserSource
import org.skepsun.kototoro.parsers.site.madara.MadaraParser

@Broken
@MangaSourceParser("BESTMANHUACOM", "BestManhua.com", "en")
internal class BestManhuaCom(context: MangaLoaderContext) :
	MadaraParser(context, MangaParserSource.BESTMANHUACOM, "bestmanhua.com", 10) {
	override val withoutAjax = true
}
