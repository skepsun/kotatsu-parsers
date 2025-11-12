package org.skepsun.kototoro.parsers.site.mangareader.es

import org.skepsun.kototoro.parsers.Broken
import org.skepsun.kototoro.parsers.MangaLoaderContext
import org.skepsun.kototoro.parsers.MangaSourceParser
import org.skepsun.kototoro.parsers.model.MangaListFilterCapabilities
import org.skepsun.kototoro.parsers.model.MangaParserSource
import org.skepsun.kototoro.parsers.site.mangareader.MangaReaderParser

@Broken
@MangaSourceParser("TECNOSCANN", "TecnoScann", "es")
internal class TecnoScann(context: MangaLoaderContext) :
	MangaReaderParser(context, MangaParserSource.TECNOSCANN, "tecnoscann.com", 20, 10) {
	override val filterCapabilities: MangaListFilterCapabilities
		get() = super.filterCapabilities.copy(
			isTagsExclusionSupported = false,
		)
}
