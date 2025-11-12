package org.skepsun.kototoro.parsers.site.ru.rulib

import org.skepsun.kototoro.parsers.Broken
import org.skepsun.kototoro.parsers.MangaLoaderContext
import org.skepsun.kototoro.parsers.MangaSourceParser
import org.skepsun.kototoro.parsers.model.ContentType
import org.skepsun.kototoro.parsers.model.MangaParserSource

@Broken
@MangaSourceParser("HENTAILIB", "HentaiLib", "ru", type = ContentType.HENTAI)
internal class HentaiLibParser(context: MangaLoaderContext) : LibSocialParser(
	context = context,
	source = MangaParserSource.HENTAILIB,
	siteId = 4,
	siteDomains = arrayOf("v1.hentailib.org", "hentailib.me"),
)
