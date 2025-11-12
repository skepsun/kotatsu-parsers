package org.skepsun.kototoro.parsers.site.madara.fr

import org.skepsun.kototoro.parsers.MangaLoaderContext
import org.skepsun.kototoro.parsers.MangaSourceParser
import org.skepsun.kototoro.parsers.model.ContentType
import org.skepsun.kototoro.parsers.model.MangaParserSource
import org.skepsun.kototoro.parsers.site.madara.MadaraParser

@MangaSourceParser("MANGAHUB", "MangaHub", "fr", ContentType.HENTAI)
internal class MangaHub(context: MangaLoaderContext) :
	MadaraParser(context, MangaParserSource.MANGAHUB, "mangahub.fr") {
	override val datePattern = "d MMMM yyyy"
}
