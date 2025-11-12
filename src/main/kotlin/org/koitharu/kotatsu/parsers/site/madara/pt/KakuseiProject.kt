package org.skepsun.kototoro.parsers.site.madara.pt

import org.skepsun.kototoro.parsers.MangaLoaderContext
import org.skepsun.kototoro.parsers.MangaSourceParser
import org.skepsun.kototoro.parsers.model.MangaParserSource
import org.skepsun.kototoro.parsers.site.madara.MadaraParser

@MangaSourceParser("KAKUSEIPROJECT", "KakuseiProject", "pt")
internal class KakuseiProject(context: MangaLoaderContext) :
	MadaraParser(context, MangaParserSource.KAKUSEIPROJECT, "kakuseiproject.com", 10)
