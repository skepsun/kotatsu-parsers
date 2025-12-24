package org.skepsun.kototoro.parsers.exception

import okio.IOException
import org.skepsun.kototoro.parsers.InternalParsersApi
import org.skepsun.kototoro.parsers.model.MangaSource

/**
 * Authorization is required for access to the requested content
 */
public class AuthRequiredException @InternalParsersApi @JvmOverloads constructor(
	public val source: MangaSource,
	cause: Throwable? = null,
) : IOException("Authorization required", cause)
