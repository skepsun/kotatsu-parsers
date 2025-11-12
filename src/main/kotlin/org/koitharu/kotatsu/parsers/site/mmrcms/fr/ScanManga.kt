package org.skepsun.kototoro.parsers.site.mmrcms.fr

import org.skepsun.kototoro.parsers.Broken
import org.skepsun.kototoro.parsers.MangaLoaderContext
import org.skepsun.kototoro.parsers.MangaSourceParser
import org.skepsun.kototoro.parsers.model.MangaParserSource
import org.skepsun.kototoro.parsers.site.mmrcms.MmrcmsParser
import java.util.*

@Broken
@MangaSourceParser("SCANMANGA", "ScanManga", "fr")
internal class ScanManga(context: MangaLoaderContext) :
	MmrcmsParser(context, MangaParserSource.SCANMANGA, "scan-manga.me") {
	override val imgUpdated = ".jpg"
	override val sourceLocale: Locale = Locale.ENGLISH
}
