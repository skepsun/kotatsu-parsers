package org.skepsun.kototoro.parsers.site.madara.es

import org.skepsun.kototoro.parsers.MangaLoaderContext
import org.skepsun.kototoro.parsers.MangaSourceParser
import org.skepsun.kototoro.parsers.model.MangaParserSource
import org.skepsun.kototoro.parsers.site.madara.MadaraParser

@MangaSourceParser("DRAGONTRANSLATIONORG", "DragonTranslation.org", "es")
internal class DragonTranslationOrg(context: MangaLoaderContext) :
	MadaraParser(context, MangaParserSource.DRAGONTRANSLATIONORG, "dragontranslation.org", 16) {
	override val datePattern = "dd/MM/yyyy"
}
