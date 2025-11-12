package org.skepsun.kototoro.parsers.site.madara.de

import org.skepsun.kototoro.parsers.Broken
import org.skepsun.kototoro.parsers.MangaLoaderContext
import org.skepsun.kototoro.parsers.MangaSourceParser
import org.skepsun.kototoro.parsers.model.MangaParserSource
import org.skepsun.kototoro.parsers.site.madara.MadaraParser

@Broken
@MangaSourceParser("MANGALESEN", "MangaLesen", "de")
internal class MangaLesen(context: MangaLoaderContext) :
	MadaraParser(context, MangaParserSource.MANGALESEN, "mangalesen.net")
