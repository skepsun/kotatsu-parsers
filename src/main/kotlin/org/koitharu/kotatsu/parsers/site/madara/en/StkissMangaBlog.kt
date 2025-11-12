package org.skepsun.kototoro.parsers.site.madara.en

import org.skepsun.kototoro.parsers.MangaLoaderContext
import org.skepsun.kototoro.parsers.MangaSourceParser
import org.skepsun.kototoro.parsers.model.MangaParserSource
import org.skepsun.kototoro.parsers.site.madara.MadaraParser
import org.skepsun.kototoro.parsers.Broken

@Broken("Original site closed")
@MangaSourceParser("STKISSMANGABLOG", "1StKissManga.net", "en")
internal class StkissMangaBlog(context: MangaLoaderContext) :
	MadaraParser(context, MangaParserSource.STKISSMANGABLOG, "1stkissmanga.org", 20) {
            override val postReq = true
      }
