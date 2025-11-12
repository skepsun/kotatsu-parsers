package org.skepsun.kototoro.parsers.site.madara.pt

import org.skepsun.kototoro.parsers.MangaLoaderContext
import org.skepsun.kototoro.parsers.MangaSourceParser
import org.skepsun.kototoro.parsers.model.MangaParserSource
import org.skepsun.kototoro.parsers.site.madara.MadaraParser

@MangaSourceParser("GHOSTSCAN", "GhostScan", "pt")
internal class GhostScan(context: MangaLoaderContext) :
	MadaraParser(context, MangaParserSource.GHOSTSCAN, "ghostscan.xyz", 24) {
	override val datePattern: String = "dd 'de' MMMMM 'de' yyyy"
}
