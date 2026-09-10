package org.koitharu.kotatsu.parsers.site.zh

import kotlinx.coroutines.runBlocking
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertFalse
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test
import org.skepsun.kototoro.parsers.ContentLoaderContextMock
import org.skepsun.kototoro.parsers.model.ContentChapter
import org.skepsun.kototoro.parsers.model.ContentParserSource
import org.skepsun.kototoro.parsers.site.zh.NoveliaWenku
import org.skepsun.kototoro.parsers.util.urlDecode
import org.skepsun.kototoro.parsers.util.urlEncoded

/**
 * 离线测试：验证文库 EPUB 下载 URL 的构造格式。
 *
 * 服务端（Ktor）要求 translations 以重复查询参数传递（translations=a&translations=b），
 * 逗号拼接的单值会触发 500 "Can't transform call to resource"。
 */
class NoveliaWenkuTest {

    private val parser = NoveliaWenku(ContentLoaderContextMock)

    private val novelId = "6aa19f5a53d2582e83b24316"

    private fun chapter(volumeId: String, zh: Boolean = false) = ContentChapter(
        id = 0L,
        title = volumeId,
        number = 1f,
        volume = if (zh) 0 else 1,
        url = if (zh) {
            "/wenku/$novelId/${volumeId.urlEncoded()}/zh"
        } else {
            "/wenku/$novelId/${volumeId.urlEncoded()}"
        },
        scanlator = null,
        uploadDate = 0,
        branch = null,
        source = ContentParserSource.NOVELIA_WENKU,
    )

    @Test
    fun `epub url repeats translations params`() = runBlocking {
        val volumeId = "テスト巻　Ｖｏｌｕｍｅ　１.epub"
        val pages = parser.getPages(chapter(volumeId))
        assertEquals(1, pages.size)

        val url = pages.first().url
        val path = url.substringBefore("?")
        val query = url.substringAfter("?")

        assertEquals("https://n.novelia.cc/api/wenku/$novelId/file/$volumeId", path)
        assertTrue(query.contains("mode=zh"), "mode 默认应为 zh: $query")
        assertTrue(query.contains("translationsMode=priority"), "默认应为优先模式: $query")
        // 核心回归：translations 必须是重复参数，逗号单值会触发服务端 500
        assertTrue(
            query.contains("translations=sakura&translations=gpt&translations=youdao"),
            "translations 应作为重复参数传递: $query",
        )
        assertFalse(
            query.contains("translations=sakura%2C"),
            "translations 不得以逗号单值传递: $query",
        )
        // filename = {mode}.{Y|B}{翻译源首字母}.{volumeId}
        val filename = query.substringAfter("filename=").substringBefore("&").urlDecode()
        assertEquals("zh.Ysgy.$volumeId", filename)
    }

    @Test
    fun `epub url appends epub suffix when missing`() = runBlocking {
        val volumeId = "テスト巻 ２"
        val pages = parser.getPages(chapter(volumeId, zh = true))
        val url = pages.first().url

        assertTrue(url.contains("/api/wenku/$novelId/file/$volumeId.epub"), "缺少 .epub 后缀时应补上: $url")
    }
}
