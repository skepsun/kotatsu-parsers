package org.skepsun.kototoro.parsers.site.madara.en

import org.skepsun.kototoro.parsers.Broken
import org.skepsun.kototoro.parsers.MangaLoaderContext
import org.skepsun.kototoro.parsers.MangaSourceParser
import org.skepsun.kototoro.parsers.model.MangaParserSource
import org.skepsun.kototoro.parsers.site.madara.MadaraParser

@Broken
@MangaSourceParser("NEWMANHUA", "NewManhua", "en")
internal class NewManhua(context: MangaLoaderContext) :
	MadaraParser(context, MangaParserSource.NEWMANHUA, "newmanhua.com", pageSize = 16)
