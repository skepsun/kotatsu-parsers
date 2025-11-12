package org.skepsun.kototoro.parsers.site.mangareader.tr

import org.skepsun.kototoro.parsers.MangaLoaderContext
import org.skepsun.kototoro.parsers.MangaSourceParser
import org.skepsun.kototoro.parsers.model.MangaParserSource
import org.skepsun.kototoro.parsers.site.mangareader.MangaReaderParser

@MangaSourceParser("ASEMIFANSUB", "AsemiFansub", "tr")
internal class AsemiFansub(context: MangaLoaderContext) :
	MangaReaderParser(context, MangaParserSource.ASEMIFANSUB, "asemifansub.com", pageSize = 20, searchPageSize = 10)
