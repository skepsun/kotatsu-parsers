package org.skepsun.kototoro.parsers.site.ru.rulib

import org.skepsun.kototoro.parsers.MangaLoaderContext
import org.skepsun.kototoro.parsers.MangaSourceParser
import org.skepsun.kototoro.parsers.exception.AuthRequiredException
import org.skepsun.kototoro.parsers.exception.NotFoundException
import org.skepsun.kototoro.parsers.model.MangaChapter
import org.skepsun.kototoro.parsers.model.MangaPage
import org.skepsun.kototoro.parsers.model.MangaParserSource

@MangaSourceParser("MANGALIB", "MangaLib", "ru")
internal class MangaLibParser(
	context: MangaLoaderContext,
) : LibSocialParser(
	context = context,
	source = MangaParserSource.MANGALIB,
	siteId = 1,
	siteDomains = arrayOf("mangalib.org", "mangalib.me"),
) {

	override suspend fun getPages(chapter: MangaChapter): List<MangaPage> = try {
		super.getPages(chapter)
	} catch (e: NotFoundException) {
		throw AuthRequiredException(source, e)
	}
}
