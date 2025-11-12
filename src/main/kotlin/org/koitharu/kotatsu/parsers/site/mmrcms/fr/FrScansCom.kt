package org.skepsun.kototoro.parsers.site.mmrcms.fr

import org.skepsun.kototoro.parsers.Broken
import org.skepsun.kototoro.parsers.MangaLoaderContext
import org.skepsun.kototoro.parsers.MangaSourceParser
import org.skepsun.kototoro.parsers.model.MangaParserSource
import org.skepsun.kototoro.parsers.site.mmrcms.MmrcmsParser
import java.util.*

@Broken
@MangaSourceParser("FRSCANSCOM", "FrScans.com", "fr")
internal class FrScansCom(context: MangaLoaderContext) :
	MmrcmsParser(context, MangaParserSource.FRSCANSCOM, "frscans.com") {
	override val sourceLocale: Locale = Locale.ENGLISH
}
