package org.skepsun.kototoro.parsers

import org.skepsun.kototoro.parsers.config.ConfigKey
import org.skepsun.kototoro.parsers.config.MangaSourceConfig

internal class SourceConfigMock : MangaSourceConfig {

	private val configValues = mutableMapOf<String, Any?>()

	override fun <T> get(key: ConfigKey<T>): T {
		return configValues[key.key] as? T ?: key.defaultValue
	}

	fun <T> set(key: ConfigKey<T>, value: T) {
		configValues[key.key] = value
	}
}