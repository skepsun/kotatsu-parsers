package org.skepsun.kototoro.parsers.site.madara.tr

import org.skepsun.kototoro.parsers.Broken
import org.skepsun.kototoro.parsers.MangaLoaderContext
import org.skepsun.kototoro.parsers.MangaSourceParser
import org.skepsun.kototoro.parsers.model.ContentType
import org.skepsun.kototoro.parsers.model.MangaParserSource
import org.skepsun.kototoro.parsers.site.madara.MadaraParser

@Broken
@MangaSourceParser("DECCALSCANS", "DeccalScans", "tr", ContentType.HENTAI)
internal class DeccalScans(context: MangaLoaderContext) :
	MadaraParser(context, MangaParserSource.DECCALSCANS, "fuchscans.com") {
	override val tagPrefix = "turler/"
}