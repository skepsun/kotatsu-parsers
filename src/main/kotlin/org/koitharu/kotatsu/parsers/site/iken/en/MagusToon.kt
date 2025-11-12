package org.skepsun.kototoro.parsers.site.iken.en

import org.skepsun.kototoro.parsers.MangaLoaderContext
import org.skepsun.kototoro.parsers.MangaSourceParser
import org.skepsun.kototoro.parsers.model.MangaParserSource
import org.skepsun.kototoro.parsers.site.iken.IkenParser

@MangaSourceParser("MAGUSMANGA", "MagusToon", "en")
internal class MagusToon(context: MangaLoaderContext) :
        IkenParser(context, MangaParserSource.MAGUSMANGA, "magustoon.org", 18, true)
