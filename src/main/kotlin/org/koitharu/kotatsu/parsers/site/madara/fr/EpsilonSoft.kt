package org.skepsun.kototoro.parsers.site.madara.fr

import org.skepsun.kototoro.parsers.Broken
import org.skepsun.kototoro.parsers.MangaLoaderContext
import org.skepsun.kototoro.parsers.MangaSourceParser
import org.skepsun.kototoro.parsers.model.*
import org.skepsun.kototoro.parsers.site.madara.MadaraParser

@Broken("Blocked by Cloudflare")
@MangaSourceParser("EPSILONSOFT", "EpsilonSoft", "fr")
internal class EpsilonSoft(context: MangaLoaderContext) :
	MadaraParser(context, MangaParserSource.EPSILONSOFT, "epsilonsoft.to") {
	override val datePattern = "dd/MM/yy"
	override val withoutAjax = true
}
