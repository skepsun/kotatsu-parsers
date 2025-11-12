package org.skepsun.kototoro.parsers.site.madara.ar

import org.skepsun.kototoro.parsers.Broken
import org.skepsun.kototoro.parsers.MangaLoaderContext
import org.skepsun.kototoro.parsers.MangaSourceParser
import org.skepsun.kototoro.parsers.model.MangaParserSource
import org.skepsun.kototoro.parsers.site.madara.MadaraParser

@Broken
@MangaSourceParser("SHADOWXMANGA", "ShadowXManga", "ar")
internal class ShadowxManga(context: MangaLoaderContext) :
	MadaraParser(context, MangaParserSource.SHADOWXMANGA, "www.shadowxmanga.com") {
	override val datePattern = "yyyy/MM/dd"
}
