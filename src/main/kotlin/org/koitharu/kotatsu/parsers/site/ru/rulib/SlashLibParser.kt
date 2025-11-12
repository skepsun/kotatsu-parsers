package org.skepsun.kototoro.parsers.site.ru.rulib

import org.skepsun.kototoro.parsers.MangaLoaderContext
import org.skepsun.kototoro.parsers.MangaSourceParser
import org.skepsun.kototoro.parsers.model.MangaParserSource

@MangaSourceParser("YAOILIB", "SlashLib", "ru")
internal class SlashLibParser(context: MangaLoaderContext) : LibSocialParser(
	context = context,
	source = MangaParserSource.YAOILIB,
	siteId = 2,
	siteDomains = arrayOf("v2.slashlib.me"),
)
