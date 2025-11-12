package org.skepsun.kototoro.parsers.site.madara.es

import org.skepsun.kototoro.parsers.MangaLoaderContext
import org.skepsun.kototoro.parsers.MangaSourceParser
import org.skepsun.kototoro.parsers.model.MangaParserSource
import org.skepsun.kototoro.parsers.site.madara.MadaraParser

@MangaSourceParser("NOBLESSETRANSLATIONS", "NoblesseTranslations", "es")
internal class NoblesseTranslations(context: MangaLoaderContext) :
	MadaraParser(context, MangaParserSource.NOBLESSETRANSLATIONS, "nobledicion.yoveo.xyz")
