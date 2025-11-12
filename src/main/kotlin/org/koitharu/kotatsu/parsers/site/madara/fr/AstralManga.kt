package org.skepsun.kototoro.parsers.site.madara.fr

import org.skepsun.kototoro.parsers.Broken
import org.skepsun.kototoro.parsers.MangaLoaderContext
import org.skepsun.kototoro.parsers.MangaSourceParser
import org.skepsun.kototoro.parsers.model.MangaParserSource
import org.skepsun.kototoro.parsers.site.madara.MadaraParser

@Broken("Website, come back soon")
@MangaSourceParser("ASTRALMANGA", "AstralManga", "fr")
internal class AstralManga(context: MangaLoaderContext) :
	MadaraParser(context, MangaParserSource.ASTRALMANGA, "astral-manga.fr") {
	override val datePattern = "dd/MM/yyyy"
}
