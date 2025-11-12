package org.skepsun.kototoro.parsers.site.zeistmanga.id

import org.skepsun.kototoro.parsers.MangaLoaderContext
import org.skepsun.kototoro.parsers.MangaSourceParser
import org.skepsun.kototoro.parsers.model.MangaParserSource
import org.skepsun.kototoro.parsers.site.zeistmanga.ZeistMangaParser
import org.skepsun.kototoro.parsers.Broken

@Broken("Original site closed")
@MangaSourceParser("SOBATMANKU", "Sobatmanku", "id")
internal class Sobatmanku(context: MangaLoaderContext) :
	ZeistMangaParser(context, MangaParserSource.SOBATMANKU, "www.sobatmanku19.cab")
