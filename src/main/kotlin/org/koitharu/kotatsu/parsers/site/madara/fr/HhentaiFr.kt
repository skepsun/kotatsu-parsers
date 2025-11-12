package org.skepsun.kototoro.parsers.site.madara.fr

import org.skepsun.kototoro.parsers.MangaLoaderContext
import org.skepsun.kototoro.parsers.MangaSourceParser
import org.skepsun.kototoro.parsers.model.ContentType
import org.skepsun.kototoro.parsers.model.MangaParserSource
import org.skepsun.kototoro.parsers.site.madara.MadaraParser
import org.skepsun.kototoro.parsers.util.insertCookies

@MangaSourceParser("HHENTAIFR", "H-Hentai", "fr", ContentType.HENTAI)
internal class HhentaiFr(context: MangaLoaderContext) :
	MadaraParser(context, MangaParserSource.HHENTAIFR, "hhentai.fr") {

	init {
		context.cookieJar.insertCookies(
			domain,
			"age_gate=32;",
		)
	}
}
