package org.skepsun.kototoro.parsers.site.madara.en

import org.skepsun.kototoro.parsers.Broken
import org.skepsun.kototoro.parsers.MangaLoaderContext
import org.skepsun.kototoro.parsers.MangaSourceParser
import org.skepsun.kototoro.parsers.model.MangaParserSource
import org.skepsun.kototoro.parsers.site.madara.MadaraParser

@Broken
@MangaSourceParser("MANGA68", "Manga68", "en")
internal class Manga68(context: MangaLoaderContext) :
	MadaraParser(context, MangaParserSource.MANGA68, "manga68.com") {
	override val withoutAjax = true
}
