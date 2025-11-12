package org.skepsun.kototoro.parsers.site.madara.en

import org.skepsun.kototoro.parsers.MangaLoaderContext
import org.skepsun.kototoro.parsers.MangaSourceParser
import org.skepsun.kototoro.parsers.model.ContentType
import org.skepsun.kototoro.parsers.model.MangaParserSource
import org.skepsun.kototoro.parsers.site.madara.MadaraParser

@MangaSourceParser("MANYTOONME", "ManyToon.me", "en", ContentType.HENTAI)
internal class ManyToonMe(context: MangaLoaderContext) :
	MadaraParser(context, MangaParserSource.MANYTOONME, "manytoon.me", 20) {
	override val listUrl = "manhwa/"
	override val tagPrefix = "manhwa-genre/"
}
