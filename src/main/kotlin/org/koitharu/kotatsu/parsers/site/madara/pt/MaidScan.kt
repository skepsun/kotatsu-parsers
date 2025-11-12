package org.skepsun.kototoro.parsers.site.madara.pt

import org.skepsun.kototoro.parsers.MangaLoaderContext
import org.skepsun.kototoro.parsers.MangaSourceParser
import org.skepsun.kototoro.parsers.model.ContentType
import org.skepsun.kototoro.parsers.model.MangaParserSource
import org.skepsun.kototoro.parsers.site.madara.MadaraParser

@MangaSourceParser("MAIDSCAN", "MaidScan", "pt", ContentType.HENTAI)
internal class MaidScan(context: MangaLoaderContext) :
	MadaraParser(context, MangaParserSource.MAIDSCAN, "novo.empreguetes.site", 10) {
	override val datePattern: String = "dd/MM/yyyy"
}
