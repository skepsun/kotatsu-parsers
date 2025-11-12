package org.skepsun.kototoro.parsers.site.madara.pt

import org.skepsun.kototoro.parsers.MangaLoaderContext
import org.skepsun.kototoro.parsers.MangaSourceParser
import org.skepsun.kototoro.parsers.model.ContentType
import org.skepsun.kototoro.parsers.model.MangaParserSource
import org.skepsun.kototoro.parsers.site.madara.MadaraParser

@MangaSourceParser("APENASMAISUMYAOI", "Apenasmaisum Yaoi", "pt", ContentType.HENTAI)
internal class ApenasmaisumYaoi(context: MangaLoaderContext) :
	MadaraParser(context, MangaParserSource.APENASMAISUMYAOI, "apenasmaisumyaoi.com") {
	override val datePattern: String = "dd 'de' MMMMM 'de' yyyy"
}
