package org.skepsun.kototoro.parsers.site.zh

import kotlinx.coroutines.runBlocking
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.condition.EnabledIfEnvironmentVariable
import org.skepsun.kototoro.parsers.ContentLoaderContextMock
import org.skepsun.kototoro.parsers.model.Content
import org.skepsun.kototoro.parsers.model.ContentListFilter
import org.skepsun.kototoro.parsers.model.SortOrder

/**
 * 在线验证文库 EPUB 下载链路：列表 → 详情 → EPUB 下载地址 → 文件可下载。
 *
 * 回归背景：translations 若以逗号单值传递，服务端（Ktor）反序列化失败返回
 * 500 "Can't transform call to resource"；必须作为重复查询参数传递。
 *
 * 启用方式: 设置环境变量 NOVELIA_WENKU_INTEGRATION_TEST=1 后运行；
 * 代理（如有）通过 HTTPS_PROXY/HTTP_PROXY/ALL_PROXY 环境变量生效。
 */
@EnabledIfEnvironmentVariable(named = "NOVELIA_WENKU_INTEGRATION_TEST", matches = "1")
class NoveliaWenkuIntegrationTest {

    private val parser = NoveliaWenku(ContentLoaderContextMock)

    @Test
    fun `list detail and epub download`() = runBlocking {
        val list = parser.getListPage(1, SortOrder.UPDATED, ContentListFilter())
        assertTrue(list.isNotEmpty(), "列表为空")

        // 在前几个小说中找一个有 EPUB 卷的
        var detailed: Content? = null
        for (item in list.take(5)) {
            val candidate = runCatching { parser.getDetails(item) }.getOrNull() ?: continue
            if (candidate.chapters.orEmpty().isNotEmpty()) {
                detailed = candidate
                break
            }
        }
        val chapters = detailed?.chapters.orEmpty()
        assertTrue(chapters.isNotEmpty(), "前几个小说均无 EPUB 卷")
        val chapter = chapters.first()

        val pages = parser.getPages(chapter)
        assertTrue(pages.isNotEmpty(), "EPUB 下载页为空")
        val url = pages.first().url
        assertTrue(url.contains("/api/wenku/") && url.contains("translations="), "下载地址异常: $url")

        // 下载地址走 OkHttp 实际请求验证（302 → 200，EPUB 为 ZIP 格式，魔数 PK）
        val response = ContentLoaderContextMock.doRequest(url, null)
        response.use {
            assertEquals(200, it.code, "EPUB 下载失败（500 = translations 参数格式错误）: $url")
            val bytes = it.body!!.bytes()
            assertTrue(bytes.size > 1000, "EPUB 文件过小: ${bytes.size}")
            assertEquals("PK", String(bytes, 0, 2, Charsets.US_ASCII), "EPUB 缺少 ZIP 魔数: $url")
        }
    }
}
