package org.skepsun.kototoro.parsers.site.mangareader.fr

import org.skepsun.kototoro.parsers.Broken
import org.skepsun.kototoro.parsers.MangaLoaderContext
import org.skepsun.kototoro.parsers.MangaSourceParser
import org.skepsun.kototoro.parsers.model.MangaParserSource
import org.skepsun.kototoro.parsers.site.mangareader.MangaReaderParser

@Broken
@MangaSourceParser("BANANASCAN", "BananaScan", "fr")
internal class BananaScan(context: MangaLoaderContext) :
	MangaReaderParser(context, MangaParserSource.BANANASCAN, "banana-scan.com", pageSize = 20, searchPageSize = 20) {
	override val datePattern = "MMM d, yyyy"
}
