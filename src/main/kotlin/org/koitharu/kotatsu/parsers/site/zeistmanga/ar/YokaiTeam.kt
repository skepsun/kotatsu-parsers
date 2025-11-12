package org.skepsun.kototoro.parsers.site.zeistmanga.ar

import org.skepsun.kototoro.parsers.MangaLoaderContext
import org.skepsun.kototoro.parsers.MangaSourceParser
import org.skepsun.kototoro.parsers.model.MangaParserSource
import org.skepsun.kototoro.parsers.site.zeistmanga.ZeistMangaParser

@MangaSourceParser("YOKAITEAM", "YokaiTeam", "ar")
internal class YokaiTeam(context: MangaLoaderContext) :
	ZeistMangaParser(context, MangaParserSource.YOKAITEAM, "yokai-team.blogspot.com")
