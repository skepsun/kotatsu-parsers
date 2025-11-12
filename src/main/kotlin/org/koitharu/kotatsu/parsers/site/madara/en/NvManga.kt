package org.skepsun.kototoro.parsers.site.madara.en

import org.skepsun.kototoro.parsers.Broken
import org.skepsun.kototoro.parsers.MangaLoaderContext
import org.skepsun.kototoro.parsers.MangaSourceParser
import org.skepsun.kototoro.parsers.model.MangaParserSource
import org.skepsun.kototoro.parsers.site.madara.MadaraParser

@Broken
@MangaSourceParser("NVMANGA", "NvManga", "en")
internal class NvManga(context: MangaLoaderContext) :
	MadaraParser(context, MangaParserSource.NVMANGA, "1manhwa.com") {
	override val datePattern = "dd/MM/yyyy"
	override val tagPrefix = "genre/"
	override val listUrl = "webtoon/"
}
