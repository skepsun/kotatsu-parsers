package org.skepsun.kototoro.parsers.config

public interface MangaSourceConfig {

	public operator fun <T> get(key: ConfigKey<T>): T
}
