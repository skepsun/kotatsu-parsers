package org.skepsun.kototoro.parsers.site.otakusanctuary.vi

import org.skepsun.kototoro.parsers.MangaLoaderContext
import org.skepsun.kototoro.parsers.MangaSourceParser
import org.skepsun.kototoro.parsers.model.MangaParserSource
import org.skepsun.kototoro.parsers.site.otakusanctuary.OtakuSanctuaryParser
import org.skepsun.kototoro.parsers.Broken

@Broken("Original site closed")
@MangaSourceParser("OTAKUSAN_VI", "Otaku Sanctuary (VN)", "vi")
internal class OtakusanVi(context: MangaLoaderContext) :
	OtakuSanctuaryParser(context, MangaParserSource.OTAKUSAN_VI, "otakusan.me") {
	override val selectState = ".table-info tr:contains(Status) td"
	override val lang = "vn"
}
