package org.skepsun.kototoro.parsers.site.mmrcms.fr

import org.skepsun.kototoro.parsers.Broken
import org.skepsun.kototoro.parsers.MangaLoaderContext
import org.skepsun.kototoro.parsers.MangaSourceParser
import org.skepsun.kototoro.parsers.model.MangaParserSource
import org.skepsun.kototoro.parsers.site.mmrcms.MmrcmsParser
import java.util.*

@Broken
@MangaSourceParser("SCANMANGAVF_WS", "ScanMangaVf.ws", "fr")
internal class ScanMangaVfWs(context: MangaLoaderContext) :
	MmrcmsParser(context, MangaParserSource.SCANMANGAVF_WS, "scanmanga-vf.me") {
	override val imgUpdated = ".jpg"
	override val selectTag = "dt:contains(Genres)"
	override val selectAlt = "dt:contains(Appelé aussi)"
	override val sourceLocale: Locale = Locale.ENGLISH
}
