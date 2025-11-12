package org.skepsun.kototoro.parsers.site.madara.en

import org.skepsun.kototoro.parsers.Broken
import org.skepsun.kototoro.parsers.MangaLoaderContext
import org.skepsun.kototoro.parsers.MangaSourceParser
import org.skepsun.kototoro.parsers.model.*
import org.skepsun.kototoro.parsers.site.madara.MadaraParser
import java.util.*

@Broken("Redirect to @XMANHWA")
@MangaSourceParser("INSTAMANHWA", "InstaManhwa", "en", ContentType.HENTAI)
internal class InstaManhwa(context: MangaLoaderContext) :
	MadaraParser(context, MangaParserSource.INSTAMANHWA, "www.manhwaden.com", 15) {
	override val sourceLocale: Locale = Locale.ENGLISH
	override val selectPage = "img"
}
