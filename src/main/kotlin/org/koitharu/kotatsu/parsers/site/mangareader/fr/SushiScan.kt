package org.skepsun.kototoro.parsers.site.mangareader.fr

import org.skepsun.kototoro.parsers.Broken
import org.skepsun.kototoro.parsers.MangaLoaderContext
import org.skepsun.kototoro.parsers.MangaSourceParser
import org.skepsun.kototoro.parsers.model.MangaParserSource
import org.skepsun.kototoro.parsers.site.mangareader.MangaReaderParser

@Broken("Blocked by Cloudflare")
@MangaSourceParser("SUSHISCAN", "SushiScan.Net", "fr")
internal class SushiScan(context: MangaLoaderContext) :
	MangaReaderParser(context, MangaParserSource.SUSHISCAN, "sushiscan.net", pageSize = 20, searchPageSize = 10) {
	override val listUrl = "/catalogue"
	override val datePattern = "MMM d, yyyy"
}
