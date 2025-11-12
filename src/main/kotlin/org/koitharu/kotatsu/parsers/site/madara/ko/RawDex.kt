package org.skepsun.kototoro.parsers.site.madara.ko

import org.skepsun.kototoro.parsers.MangaLoaderContext
import org.skepsun.kototoro.parsers.MangaSourceParser
import org.skepsun.kototoro.parsers.model.ContentType
import org.skepsun.kototoro.parsers.model.MangaParserSource
import org.skepsun.kototoro.parsers.site.madara.MadaraParser
import java.util.*

@MangaSourceParser("RAWDEX", "RawDex", "ko", ContentType.HENTAI)
internal class RawDex(context: MangaLoaderContext) :
	MadaraParser(context, MangaParserSource.RAWDEX, "rawdex.net", 40) {
	override val sourceLocale: Locale = Locale.ENGLISH
}
