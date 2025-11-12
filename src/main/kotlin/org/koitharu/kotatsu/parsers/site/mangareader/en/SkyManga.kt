package org.skepsun.kototoro.parsers.site.mangareader.en

import org.skepsun.kototoro.parsers.MangaLoaderContext
import org.skepsun.kototoro.parsers.MangaSourceParser
import org.skepsun.kototoro.parsers.model.MangaParserSource
import org.skepsun.kototoro.parsers.site.mangareader.MangaReaderParser
import org.skepsun.kototoro.parsers.Broken

@Broken
@MangaSourceParser("SKY_MANGA", "SkyManga", "en")
internal class SkyManga(context: MangaLoaderContext) :
	MangaReaderParser(context, MangaParserSource.SKY_MANGA, "skymanga.work", pageSize = 20, searchPageSize = 20) {
	override val listUrl = "/manga-list"
	override val datePattern = "dd-MM-yyy"
}
