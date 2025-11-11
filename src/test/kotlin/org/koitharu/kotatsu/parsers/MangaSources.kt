package org.koitharu.kotatsu.parsers

import org.junit.jupiter.params.provider.EnumSource
import org.koitharu.kotatsu.parsers.model.MangaParserSource

// Change 'names' to test specified parsers
@EnumSource(MangaParserSource::class, names = ["WNACG", "COPYMANGA"], mode = EnumSource.Mode.INCLUDE)
internal annotation class MangaSources
