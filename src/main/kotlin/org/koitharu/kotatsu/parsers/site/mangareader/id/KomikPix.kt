package org.skepsun.kototoro.parsers.site.mangareader.id

import org.skepsun.kototoro.parsers.Broken
import org.skepsun.kototoro.parsers.MangaLoaderContext
import org.skepsun.kototoro.parsers.MangaSourceParser
import org.skepsun.kototoro.parsers.model.ContentType
import org.skepsun.kototoro.parsers.model.MangaParserSource
import org.skepsun.kototoro.parsers.site.mangareader.MangaReaderParser

@Broken
@MangaSourceParser("KOMIKPIX", "KomikPix", "id", ContentType.HENTAI)
internal class KomikPix(context: MangaLoaderContext) :
	MangaReaderParser(context, MangaParserSource.KOMIKPIX, "komikpix.com", pageSize = 30, searchPageSize = 14) {
	override val listUrl = "/hentai"
}