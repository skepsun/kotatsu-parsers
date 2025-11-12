package org.skepsun.kototoro.parsers.site.madara.es

import org.skepsun.kototoro.parsers.MangaLoaderContext
import org.skepsun.kototoro.parsers.MangaSourceParser
import org.skepsun.kototoro.parsers.model.MangaParserSource
import org.skepsun.kototoro.parsers.site.madara.MadaraParser
import org.skepsun.kototoro.parsers.Broken

@Broken
@MangaSourceParser("INMORALNOFANSUB", "InmoralNoFansub", "es")
internal class InmoralNoFansub(context: MangaLoaderContext) :
	MadaraParser(context, MangaParserSource.INMORALNOFANSUB, "inmoralnofansub.xyz") {
	override val datePattern = "dd/MM/yyyy"
}
