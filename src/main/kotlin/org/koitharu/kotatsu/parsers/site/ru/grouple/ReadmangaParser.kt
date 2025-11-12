package org.skepsun.kototoro.parsers.site.ru.grouple

import org.skepsun.kototoro.parsers.MangaLoaderContext
import org.skepsun.kototoro.parsers.MangaSourceParser
import org.skepsun.kototoro.parsers.config.ConfigKey
import org.skepsun.kototoro.parsers.model.MangaParserSource

@MangaSourceParser("READMANGA_RU", "ReadManga", "ru")
internal class ReadmangaParser(
    context: MangaLoaderContext,
) : GroupleParser(context, MangaParserSource.READMANGA_RU, 1) {

    override val configKeyDomain = ConfigKey.Domain(*domains)

    override fun getRequestHeaders() = super.getRequestHeaders().newBuilder()
        .add("referer", "https://$domain/")
        .build()

    companion object {

        val domains = arrayOf(
            "a.zazaza.me",
            "3.readmanga.ru",
        )
    }
}
