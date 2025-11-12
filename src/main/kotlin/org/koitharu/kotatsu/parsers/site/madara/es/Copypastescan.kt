package org.skepsun.kototoro.parsers.site.madara.es

import org.skepsun.kototoro.parsers.Broken
import org.skepsun.kototoro.parsers.MangaLoaderContext
import org.skepsun.kototoro.parsers.MangaSourceParser
import org.skepsun.kototoro.parsers.model.MangaParserSource
import org.skepsun.kototoro.parsers.site.madara.MadaraParser

@Broken
@MangaSourceParser("COPYPASTESCAN", "CopyPasteScan", "es")
internal class Copypastescan(context: MangaLoaderContext) :
	MadaraParser(context, MangaParserSource.COPYPASTESCAN, "copypastescan.xyz", 10) {
	override val datePattern = "d MMMM, yyyy"
}
