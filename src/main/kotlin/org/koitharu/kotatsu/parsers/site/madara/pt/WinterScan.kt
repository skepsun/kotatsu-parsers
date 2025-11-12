package org.skepsun.kototoro.parsers.site.madara.pt

import org.skepsun.kototoro.parsers.MangaLoaderContext
import org.skepsun.kototoro.parsers.MangaSourceParser
import org.skepsun.kototoro.parsers.model.MangaParserSource
import org.skepsun.kototoro.parsers.site.madara.MadaraParser

@MangaSourceParser("WINTERSCAN", "WinterScan", "pt")
internal class WinterScan(context: MangaLoaderContext) :
	MadaraParser(context, MangaParserSource.WINTERSCAN, "winterscan.com", pageSize = 20) {
	override val datePattern: String = "dd 'de' MMMMM 'de' yyyy"
}
