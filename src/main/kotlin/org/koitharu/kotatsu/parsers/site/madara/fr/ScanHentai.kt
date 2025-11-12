package org.skepsun.kototoro.parsers.site.madara.fr

import org.skepsun.kototoro.parsers.Broken
import org.skepsun.kototoro.parsers.MangaLoaderContext
import org.skepsun.kototoro.parsers.MangaSourceParser
import org.skepsun.kototoro.parsers.model.ContentType
import org.skepsun.kototoro.parsers.model.MangaParserSource
import org.skepsun.kototoro.parsers.site.madara.MadaraParser

@Broken
@MangaSourceParser("SCANHENTAI", "ScanHentai", "fr", ContentType.HENTAI)
internal class ScanHentai(context: MangaLoaderContext) :
	MadaraParser(context, MangaParserSource.SCANHENTAI, "scan-hentai.fr") {
	override val datePattern = "dd/MM/yyyy"
}
