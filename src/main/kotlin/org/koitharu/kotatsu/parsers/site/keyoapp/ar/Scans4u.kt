package org.skepsun.kototoro.parsers.site.keyoapp.ar

import org.skepsun.kototoro.parsers.MangaLoaderContext
import org.skepsun.kototoro.parsers.MangaSourceParser
import org.skepsun.kototoro.parsers.model.MangaParserSource
import org.skepsun.kototoro.parsers.site.keyoapp.KeyoappParser

@MangaSourceParser("SCANS4U", "4uScans", "ar")
internal class Scans4u(context: MangaLoaderContext) :
	KeyoappParser(context, MangaParserSource.SCANS4U, "4uscans.com")
