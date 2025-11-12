package org.skepsun.kototoro.parsers.site.mangareader.tr

import org.skepsun.kototoro.parsers.MangaLoaderContext
import org.skepsun.kototoro.parsers.MangaSourceParser
import org.skepsun.kototoro.parsers.model.MangaParserSource
import org.skepsun.kototoro.parsers.site.mangareader.MangaReaderParser
import org.skepsun.kototoro.parsers.Broken

@Broken
@MangaSourceParser("MANGAKAZANI", "MangaKazani", "tr")
internal class MangaKazani(context: MangaLoaderContext) :
	MangaReaderParser(context, MangaParserSource.MANGAKAZANI, "mangakazani.com", pageSize = 19, searchPageSize = 10) {
	override val listUrl = "/seriler"
}
