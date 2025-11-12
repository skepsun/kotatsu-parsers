package org.skepsun.kototoro.parsers.site.zeistmanga.id

import org.skepsun.kototoro.parsers.Broken
import org.skepsun.kototoro.parsers.MangaLoaderContext
import org.skepsun.kototoro.parsers.MangaSourceParser
import org.skepsun.kototoro.parsers.model.ContentType
import org.skepsun.kototoro.parsers.model.MangaParserSource
import org.skepsun.kototoro.parsers.model.MangaTag
import org.skepsun.kototoro.parsers.site.zeistmanga.ZeistMangaParser
import org.skepsun.kototoro.parsers.util.*

@Broken
@MangaSourceParser("MIKOROKU", "Mikoroku", "id", ContentType.HENTAI)
internal class Mikoroku(context: MangaLoaderContext) :
	ZeistMangaParser(context, MangaParserSource.MIKOROKU, "www.mikoroku.web.id") {

	override suspend fun fetchAvailableTags(): Set<MangaTag> {
		val doc = webClient.httpGet("https://$domain").parseHtml()
		return doc.requireElementById("Genre").select("div.items-center").mapToSet {
			MangaTag(
				key = it.selectFirstOrThrow("input").attr("value"),
				title = it.selectFirstOrThrow("label").text().substringBefore('('),
				source = source,
			)
		}
	}
}
