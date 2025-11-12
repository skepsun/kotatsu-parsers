package org.skepsun.kototoro.parsers.site.madara.es

import org.skepsun.kototoro.parsers.MangaLoaderContext
import org.skepsun.kototoro.parsers.MangaSourceParser
import org.skepsun.kototoro.parsers.model.MangaParserSource
import org.skepsun.kototoro.parsers.site.madara.MadaraParser
import org.skepsun.kototoro.parsers.Broken

@Broken
@MangaSourceParser("DAPROB", "Daprob", "es")
internal class Daprob(context: MangaLoaderContext) :
	MadaraParser(context, MangaParserSource.DAPROB, "daprob.com") {
	override val datePattern = "d 'de' MMMMM 'de' yyyy"
}
