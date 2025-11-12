package org.skepsun.kototoro.parsers.site.vmp.es

import org.skepsun.kototoro.parsers.MangaLoaderContext
import org.skepsun.kototoro.parsers.MangaSourceParser
import org.skepsun.kototoro.parsers.model.ContentType
import org.skepsun.kototoro.parsers.model.MangaParserSource
import org.skepsun.kototoro.parsers.site.vmp.VmpParser

// Other domain name : toonx.net
@MangaSourceParser("VERCOMICSPORNO", "VerComicsPorno", "es", ContentType.HENTAI)
internal class VerComicsPorno(context: MangaLoaderContext) :
	VmpParser(context, MangaParserSource.VERCOMICSPORNO, "vercomicsporno.com") {
	override val listUrl = "comics-porno/"
	override val geneUrl = "etiquetas/"
}
