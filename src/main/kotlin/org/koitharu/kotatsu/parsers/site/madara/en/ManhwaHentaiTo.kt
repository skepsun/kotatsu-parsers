package org.skepsun.kototoro.parsers.site.madara.en

import org.skepsun.kototoro.parsers.MangaLoaderContext
import org.skepsun.kototoro.parsers.MangaSourceParser
import org.skepsun.kototoro.parsers.model.ContentType
import org.skepsun.kototoro.parsers.model.MangaParserSource
import org.skepsun.kototoro.parsers.site.madara.MadaraParser

@MangaSourceParser("MANHWAHENTAITO", "ManhwaHentai.to", "en", ContentType.HENTAI)
internal class ManhwaHentaiTo(context: MangaLoaderContext) :
	MadaraParser(context, MangaParserSource.MANHWAHENTAITO, "manhwahentai.to", 10) {
	override val tagPrefix = "pornhwa-genre/"
	override val listUrl = "pornhwa/"
	override val datePattern = "d MMMM yyyy"
}
