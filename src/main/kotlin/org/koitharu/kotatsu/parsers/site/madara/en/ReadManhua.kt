package org.skepsun.kototoro.parsers.site.madara.en

import org.skepsun.kototoro.parsers.Broken
import org.skepsun.kototoro.parsers.MangaLoaderContext
import org.skepsun.kototoro.parsers.MangaSourceParser
import org.skepsun.kototoro.parsers.model.ContentType
import org.skepsun.kototoro.parsers.model.MangaParserSource
import org.skepsun.kototoro.parsers.site.madara.MadaraParser

@Broken
@MangaSourceParser("READMANHUA", "ReadManhua (Broken)", "en", ContentType.HENTAI)
internal class ReadManhua(context: MangaLoaderContext) :
	MadaraParser(context, MangaParserSource.READMANHUA, "readmanhua.net", 20) {
	override val postReq = true
	override val datePattern = "d MMM yy"
}
