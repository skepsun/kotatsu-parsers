package org.skepsun.kototoro.parsers.site.madara.es

import org.skepsun.kototoro.parsers.MangaLoaderContext
import org.skepsun.kototoro.parsers.MangaSourceParser
import org.skepsun.kototoro.parsers.model.MangaParserSource
import org.skepsun.kototoro.parsers.site.madara.MadaraParser
import org.skepsun.kototoro.parsers.Broken

@Broken
@MangaSourceParser("MMDAOS", "Mmdaos", "es")
internal class Mmdaos(context: MangaLoaderContext) :
	MadaraParser(context, MangaParserSource.MMDAOS, "mmdaos.com")
