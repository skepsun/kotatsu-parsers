package org.skepsun.kototoro.parsers.site.ru.grouple

import org.skepsun.kototoro.parsers.MangaLoaderContext
import org.skepsun.kototoro.parsers.MangaSourceParser
import org.skepsun.kototoro.parsers.config.ConfigKey
import org.skepsun.kototoro.parsers.model.MangaParserSource

@MangaSourceParser("MINTMANGA", "MintManga", "ru")
internal class MintMangaParser(
	context: MangaLoaderContext,
) : GroupleParser(context, MangaParserSource.MINTMANGA, 2) {

	override val configKeyDomain = ConfigKey.Domain(*domains)

	override fun getRequestHeaders() = super.getRequestHeaders().newBuilder()
		.add("referer", "https://$domain/")
		.build()

	companion object {

		val domains = arrayOf(
			"2.mintmanga.one",
			"24.mintmanga.one",
			"mintmanga.live",
			"mintmanga.com",
		)
	}
}
