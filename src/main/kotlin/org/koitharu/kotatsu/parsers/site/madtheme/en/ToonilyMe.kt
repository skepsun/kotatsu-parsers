package org.skepsun.kototoro.parsers.site.madtheme.en

import org.skepsun.kototoro.parsers.MangaLoaderContext
import org.skepsun.kototoro.parsers.MangaSourceParser
import org.skepsun.kototoro.parsers.model.ContentType
import org.skepsun.kototoro.parsers.model.MangaParserSource
import org.skepsun.kototoro.parsers.site.madtheme.MadthemeParser

@MangaSourceParser("TOONILY_ME", "Toonily.Me", "en", ContentType.HENTAI)
internal class ToonilyMe(context: MangaLoaderContext) :
	MadthemeParser(context, MangaParserSource.TOONILY_ME, "toonily.me") {
	override val selectDesc = "div.summary div.section-body p.content"
}
