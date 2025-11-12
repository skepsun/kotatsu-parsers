package org.skepsun.kototoro.parsers.site.madara.tr

import org.skepsun.kototoro.parsers.Broken
import org.skepsun.kototoro.parsers.MangaLoaderContext
import org.skepsun.kototoro.parsers.MangaSourceParser
import org.skepsun.kototoro.parsers.model.MangaParserSource
import org.skepsun.kototoro.parsers.site.madara.MadaraParser

@Broken
@MangaSourceParser("JELLYRING", "Jellyring", "tr")
internal class Jellyring(context: MangaLoaderContext) :
	MadaraParser(context, MangaParserSource.JELLYRING, "jellyring.co")
