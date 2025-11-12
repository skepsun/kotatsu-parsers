package org.skepsun.kototoro.parsers.site.madtheme.en

import org.skepsun.kototoro.parsers.MangaLoaderContext
import org.skepsun.kototoro.parsers.MangaSourceParser
import org.skepsun.kototoro.parsers.model.ContentType
import org.skepsun.kototoro.parsers.model.MangaParserSource
import org.skepsun.kototoro.parsers.site.madtheme.MadthemeParser

@MangaSourceParser("BEEHENTAI", "BeeHentai", "en", ContentType.HENTAI)
internal class BeeHentai(context: MangaLoaderContext) :
	MadthemeParser(context, MangaParserSource.BEEHENTAI, "beehentai.com") {
	override val selectDesc = "div.section-body"
}
