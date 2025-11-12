package org.skepsun.kototoro.parsers.site.gallery.vi

import org.skepsun.kototoro.parsers.MangaLoaderContext
import org.skepsun.kototoro.parsers.MangaSourceParser
import org.skepsun.kototoro.parsers.model.MangaParserSource
import org.skepsun.kototoro.parsers.config.ConfigKey
import org.skepsun.kototoro.parsers.model.ContentType
import org.skepsun.kototoro.parsers.site.gallery.GalleryParser

@MangaSourceParser("BUONDUA", "Buon Dua", type = ContentType.OTHER)
internal class BuonDua(context: MangaLoaderContext) :
    GalleryParser(context, MangaParserSource.BUONDUA, "buondua.com") {

    override val configKeyDomain: ConfigKey.Domain = ConfigKey.Domain(
        "buondua.com",
        "buondua.us",
    )
}