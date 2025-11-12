package org.skepsun.kototoro.parsers.site.madara.en

import org.skepsun.kototoro.parsers.Broken
import org.skepsun.kototoro.parsers.MangaLoaderContext
import org.skepsun.kototoro.parsers.MangaSourceParser
import org.skepsun.kototoro.parsers.model.MangaParserSource
import org.skepsun.kototoro.parsers.site.madara.MadaraParser

@Broken
@MangaSourceParser("MANGAOWLBLOG", "MangaOwlnet.com", "en")
internal class MangaOwlBlog(context: MangaLoaderContext) :
	MadaraParser(context, MangaParserSource.MANGAOWLBLOG, "mangaowlnet.com")
