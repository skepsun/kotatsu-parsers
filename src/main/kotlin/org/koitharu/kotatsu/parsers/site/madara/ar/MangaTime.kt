package org.skepsun.kototoro.parsers.site.madara.ar

import org.skepsun.kototoro.parsers.Broken
import org.skepsun.kototoro.parsers.MangaLoaderContext
import org.skepsun.kototoro.parsers.MangaSourceParser
import org.skepsun.kototoro.parsers.model.MangaParserSource
import org.skepsun.kototoro.parsers.site.madara.MadaraParser

@Broken
@MangaSourceParser("MANGATIME", "MangaTime", "ar")
internal class MangaTime(context: MangaLoaderContext) :
	MadaraParser(context, MangaParserSource.MANGATIME, "mangatime.org") {
	override val datePattern = "d MMMM، yyyy"
}
