package org.skepsun.kototoro.parsers.site.madara.en

import org.skepsun.kototoro.parsers.MangaLoaderContext
import org.skepsun.kototoro.parsers.MangaSourceParser
import org.skepsun.kototoro.parsers.model.ContentType
import org.skepsun.kototoro.parsers.model.MangaParserSource
import org.skepsun.kototoro.parsers.site.madara.MadaraParser

@MangaSourceParser("MANGAHENTAI", "MangaHentai", "en", ContentType.HENTAI)
internal class MangaHentai(context: MangaLoaderContext) :
	MadaraParser(context, MangaParserSource.MANGAHENTAI, "mangahentai.me", 20) {

	override val tagPrefix = "manga-hentai-genre/"
	override val listUrl = "manga-hentai/"
}
