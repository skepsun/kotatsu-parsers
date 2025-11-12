package org.skepsun.kototoro.parsers.site.heancms.pt

import org.skepsun.kototoro.parsers.Broken
import org.skepsun.kototoro.parsers.MangaLoaderContext
import org.skepsun.kototoro.parsers.MangaSourceParser
import org.skepsun.kototoro.parsers.model.*
import org.skepsun.kototoro.parsers.site.heancms.HeanCms

@Broken
@MangaSourceParser("MODESCANLATOR", "ModeScanlator", "pt")
internal class ModeScanlator(context: MangaLoaderContext) :
	HeanCms(context, MangaParserSource.MODESCANLATOR, "site.modescanlator.net") {
	override val apiPath = "api.modescanlator.net"
}

