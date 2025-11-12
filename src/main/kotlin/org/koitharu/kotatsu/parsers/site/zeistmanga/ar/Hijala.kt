package org.skepsun.kototoro.parsers.site.zeistmanga.ar

import org.skepsun.kototoro.parsers.MangaLoaderContext
import org.skepsun.kototoro.parsers.MangaSourceParser
import org.skepsun.kototoro.parsers.model.MangaParserSource
import org.skepsun.kototoro.parsers.site.zeistmanga.ZeistMangaParser

@MangaSourceParser("HIJALA", "Hijala", "ar")
internal class Hijala(context: MangaLoaderContext) :
	ZeistMangaParser(context, MangaParserSource.HIJALA, "hijala.com")
