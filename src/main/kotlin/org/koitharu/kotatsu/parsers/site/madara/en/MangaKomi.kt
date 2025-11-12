package org.skepsun.kototoro.parsers.site.madara.en

import org.skepsun.kototoro.parsers.MangaLoaderContext
import org.skepsun.kototoro.parsers.MangaSourceParser
import org.skepsun.kototoro.parsers.model.MangaParserSource
import org.skepsun.kototoro.parsers.site.madara.MadaraParser

@MangaSourceParser("MANGA_KOMI", "MangaKomi", "en")
internal class MangaKomi(context: MangaLoaderContext) :
	MadaraParser(context, MangaParserSource.MANGA_KOMI, "mangakomi.io", pageSize = 18) {
	override val datePattern = "MMMM dd, yyyy"
}
