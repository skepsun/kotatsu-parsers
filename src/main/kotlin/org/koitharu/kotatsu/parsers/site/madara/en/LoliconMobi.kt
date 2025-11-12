package org.skepsun.kototoro.parsers.site.madara.en

import org.skepsun.kototoro.parsers.Broken
import org.skepsun.kototoro.parsers.MangaLoaderContext
import org.skepsun.kototoro.parsers.MangaSourceParser
import org.skepsun.kototoro.parsers.model.ContentType
import org.skepsun.kototoro.parsers.model.MangaParserSource
import org.skepsun.kototoro.parsers.site.madara.MadaraParser

@Broken
@MangaSourceParser("LOLICONMOBI", "LoliconMobi", "en", ContentType.HENTAI)
internal class LoliconMobi(context: MangaLoaderContext) :
	MadaraParser(context, MangaParserSource.LOLICONMOBI, "lolicon.mobi") {
	override val postReq = true
	override val tagPrefix = "lolicon-genre/"
}
