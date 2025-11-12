package org.skepsun.kototoro.parsers.site.madara.en

import org.skepsun.kototoro.parsers.MangaLoaderContext
import org.skepsun.kototoro.parsers.MangaSourceParser
import org.skepsun.kototoro.parsers.model.ContentType
import org.skepsun.kototoro.parsers.model.MangaParserSource
import org.skepsun.kototoro.parsers.site.madara.MadaraParser

@MangaSourceParser("GEDECOMIX", "GedeComix", "en", ContentType.HENTAI)
internal class GedeComix(context: MangaLoaderContext) :
	MadaraParser(context, MangaParserSource.GEDECOMIX, "gedecomix.com", 18) {
	override val tagPrefix = "comics-tag/"
	override val listUrl = "porncomic/"
}
