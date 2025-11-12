package org.skepsun.kototoro.parsers.site.madara.en

import org.skepsun.kototoro.parsers.Broken
import org.skepsun.kototoro.parsers.MangaLoaderContext
import org.skepsun.kototoro.parsers.MangaSourceParser
import org.skepsun.kototoro.parsers.model.MangaParserSource
import org.skepsun.kototoro.parsers.site.madara.MadaraParser

@Broken("Redirect to @MortalsGroove")
@MangaSourceParser("IMMORTALUPDATES", "ImmortalUpdates", "en")
internal class ImmortalUpdates(context: MangaLoaderContext) :
	MadaraParser(context, MangaParserSource.IMMORTALUPDATES, "immortalupdates.com") {
	override val listUrl = "mangas/"
}
