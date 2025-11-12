package org.skepsun.kototoro.parsers.site.scan.it

import org.skepsun.kototoro.parsers.MangaLoaderContext
import org.skepsun.kototoro.parsers.MangaSourceParser
import org.skepsun.kototoro.parsers.model.MangaParserSource
import org.skepsun.kototoro.parsers.site.scan.ScanParser

@MangaSourceParser("MANGAITALIA", "MangaItalia", "it")
internal class MangaItalia(context: MangaLoaderContext) :
	ScanParser(context, MangaParserSource.MANGAITALIA, "mangaita.io")
