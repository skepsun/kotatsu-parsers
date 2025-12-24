@file:JvmName("MangaParsersUtils")

package org.skepsun.kototoro.parsers.util

import org.skepsun.kototoro.parsers.model.MangaChapter
import org.skepsun.kototoro.parsers.model.MangaListFilter
import kotlin.contracts.contract

public fun MangaListFilter?.isNullOrEmpty(): Boolean {
	contract {
		returns(false) implies (this@isNullOrEmpty != null)
	}
	return this == null || this.isEmpty()
}

public fun Collection<MangaChapter>.findById(chapterId: Long): MangaChapter? = find { x ->
	x.id == chapterId
}
