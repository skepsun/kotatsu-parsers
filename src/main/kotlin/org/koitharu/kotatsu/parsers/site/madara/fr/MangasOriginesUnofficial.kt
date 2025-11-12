package org.skepsun.kototoro.parsers.site.madara.fr

import org.skepsun.kototoro.parsers.Broken
import org.skepsun.kototoro.parsers.MangaLoaderContext
import org.skepsun.kototoro.parsers.MangaSourceParser
import org.skepsun.kototoro.parsers.model.MangaParserSource
import org.skepsun.kototoro.parsers.site.madara.MadaraParser

@Broken ("Not dead, changed template")
@MangaSourceParser("MANGASORIGINESUNOFFICIAL", "CrunchyScan", "fr")
internal class MangasOriginesUnofficial(context: MangaLoaderContext) :
	MadaraParser(context, MangaParserSource.MANGASORIGINESUNOFFICIAL, "crunchyscan.fr")
