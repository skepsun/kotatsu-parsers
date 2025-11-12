package org.skepsun.kototoro.parsers.site.madara.pt

import org.skepsun.kototoro.parsers.MangaLoaderContext
import org.skepsun.kototoro.parsers.MangaSourceParser
import org.skepsun.kototoro.parsers.model.ContentType
import org.skepsun.kototoro.parsers.model.MangaParserSource
import org.skepsun.kototoro.parsers.site.madara.MadaraParser

@MangaSourceParser("ILLUSIONSCAN", "IllusionScan", "pt", ContentType.HENTAI)
internal class IllusionScan(context: MangaLoaderContext) :
	MadaraParser(context, MangaParserSource.ILLUSIONSCAN, "illusionscan.com") {
	override val datePattern: String = "dd 'de' MMMMM 'de' yyyy"
}
