package org.skepsun.kototoro.parsers.site.madara.all

import org.skepsun.kototoro.parsers.MangaLoaderContext
import org.skepsun.kototoro.parsers.MangaSourceParser
import org.skepsun.kototoro.parsers.model.ContentType
import org.skepsun.kototoro.parsers.model.MangaParserSource
import org.skepsun.kototoro.parsers.site.madara.MadaraParser
import java.util.*

@MangaSourceParser("MANYTOON_CLUB", "ManyToon.club", "", ContentType.HENTAI)
internal class ManyToonClub(context: MangaLoaderContext) :
	MadaraParser(context, MangaParserSource.MANYTOON_CLUB, "manytoon.club") {
	override val postReq = true
	override val listUrl = "manhwa-raw/"
	override val tagPrefix = "manhwa-raw-genre/"
	override val sourceLocale: Locale = Locale.ENGLISH
}
