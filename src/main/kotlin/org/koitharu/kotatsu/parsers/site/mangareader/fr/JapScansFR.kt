package org.skepsun.kototoro.parsers.site.mangareader.fr

import org.skepsun.kototoro.parsers.Broken
import org.skepsun.kototoro.parsers.MangaLoaderContext
import org.skepsun.kototoro.parsers.MangaSourceParser
import org.skepsun.kototoro.parsers.model.MangaParserSource
import org.skepsun.kototoro.parsers.site.mangareader.MangaReaderParser
import java.util.Locale

@Broken
@MangaSourceParser("JAPSCANSFR", "JapScans.fr", "fr")
internal class JapScansFR(context: MangaLoaderContext) :
	MangaReaderParser(context, MangaParserSource.JAPSCANSFR, "japscans.fr", pageSize = 20, searchPageSize = 10) {
	override val sourceLocale: Locale = Locale.ENGLISH
}
