package org.skepsun.kototoro.parsers.site.mangareader.en

import org.skepsun.kototoro.parsers.Broken
import org.skepsun.kototoro.parsers.MangaLoaderContext
import org.skepsun.kototoro.parsers.MangaSourceParser
import org.skepsun.kototoro.parsers.model.MangaParserSource
import org.skepsun.kototoro.parsers.site.mangareader.MangaReaderParser

@Broken("Redirect to @FLIXSCANS")
@MangaSourceParser("SNOWSCANS", "SnowScans", "en")
internal class SnowScans(context: MangaLoaderContext) :
	MangaReaderParser(context, MangaParserSource.SNOWSCANS, "flixscans.net", pageSize = 20, searchPageSize = 10) {
	override val listUrl = "/series"
}
