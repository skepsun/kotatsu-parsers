package org.skepsun.kototoro.parsers.site.madara.ar

import org.skepsun.kototoro.parsers.MangaLoaderContext
import org.skepsun.kototoro.parsers.MangaSourceParser
import org.skepsun.kototoro.parsers.model.MangaParserSource
import org.skepsun.kototoro.parsers.site.madara.MadaraParser
import org.skepsun.kototoro.parsers.model.ContentType

@MangaSourceParser("NIJITRANSLATIONS", "Niji Translations", "ar", type = ContentType.HENTAI)
internal class NijiTranslations(context: MangaLoaderContext) :
	MadaraParser(context, MangaParserSource.NIJITRANSLATIONS, "niji-translations.com") {
	override val postReq = true
}
