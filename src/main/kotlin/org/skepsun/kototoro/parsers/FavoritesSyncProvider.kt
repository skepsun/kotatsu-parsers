package org.skepsun.kototoro.parsers

import org.skepsun.kototoro.parsers.model.Manga

/**
 * 可选能力：将本地收藏同步到站点账户。
 */
public interface FavoritesSyncProvider {

    public suspend fun addFavorite(manga: Manga): Boolean

    public suspend fun removeFavorite(manga: Manga): Boolean
}
