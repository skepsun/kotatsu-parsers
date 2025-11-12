package org.skepsun.kototoro.parsers.site.mangareader.pt

import org.skepsun.kototoro.parsers.Broken
import org.skepsun.kototoro.parsers.MangaLoaderContext
import org.skepsun.kototoro.parsers.MangaSourceParser
import org.skepsun.kototoro.parsers.model.MangaParserSource
import org.skepsun.kototoro.parsers.site.mangareader.MangaReaderParser

@Broken
@MangaSourceParser("FRANXXMANGAS", "FranxxMangas", "pt")
internal class FranxxMangas(context: MangaLoaderContext) :
	MangaReaderParser(context, MangaParserSource.FRANXXMANGAS, "franxxmangas.net", pageSize = 10, searchPageSize = 10)
