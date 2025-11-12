package org.skepsun.kototoro.parsers.site.mangareader.en

import org.skepsun.kototoro.parsers.MangaLoaderContext
import org.skepsun.kototoro.parsers.MangaSourceParser
import org.skepsun.kototoro.parsers.model.MangaParserSource
import org.skepsun.kototoro.parsers.site.mangareader.MangaReaderParser

@MangaSourceParser("MANGATX_CC", "MangaTx.cc", "en")
internal class MangaTxCc(context: MangaLoaderContext) :
	MangaReaderParser(context, MangaParserSource.MANGATX_CC, "mangatx.cc", 30, 21) {
	override val datePattern = "dd-MM-yyyy"
	override val listUrl = "/manga-list"
}
