package org.skepsun.kototoro.parsers.site.mangareader.ar

import org.skepsun.kototoro.parsers.MangaLoaderContext
import org.skepsun.kototoro.parsers.MangaSourceParser
import org.skepsun.kototoro.parsers.model.MangaParserSource
import org.skepsun.kototoro.parsers.site.mangareader.MangaReaderParser

@MangaSourceParser("HIJALACOM", "Hijalacom", "ar")
internal class Hijalacom(context: MangaLoaderContext) :
	MangaReaderParser(context, MangaParserSource.HIJALACOM, "hijala.com", pageSize = 30, searchPageSize = 10)
