package org.skepsun.kototoro.parsers.site.en.mtl

import org.skepsun.kototoro.parsers.Broken
import org.skepsun.kototoro.parsers.MangaLoaderContext
import org.skepsun.kototoro.parsers.MangaSourceParser
import org.skepsun.kototoro.parsers.model.MangaParserSource
import org.skepsun.kototoro.parsers.model.ContentType

@Broken
@MangaSourceParser("SOLARMTL", "SolarMTL", "en", type = ContentType.MANGA)
internal class SolarMTL(context: MangaLoaderContext):
    MTLParser(context, source = MangaParserSource.SOLARMTL, "solarmtl.com")
