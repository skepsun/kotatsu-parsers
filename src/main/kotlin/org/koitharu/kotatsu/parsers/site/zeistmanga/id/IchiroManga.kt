package org.skepsun.kototoro.parsers.site.zeistmanga.id

import org.skepsun.kototoro.parsers.MangaLoaderContext
import org.skepsun.kototoro.parsers.MangaSourceParser
import org.skepsun.kototoro.parsers.model.MangaParserSource
import org.skepsun.kototoro.parsers.site.zeistmanga.ZeistMangaParser

@MangaSourceParser("ICHIROMANGA", "IchiroManga", "id")
internal class IchiroManga(context: MangaLoaderContext) :
	ZeistMangaParser(context, MangaParserSource.ICHIROMANGA, "ichiromanga.my.id")
