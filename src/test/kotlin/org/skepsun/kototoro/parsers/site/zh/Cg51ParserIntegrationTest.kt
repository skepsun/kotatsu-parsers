package org.skepsun.kototoro.parsers.site.zh

import kotlinx.coroutines.runBlocking
import okhttp3.Request
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertFalse
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.condition.EnabledIfEnvironmentVariable
import org.skepsun.kototoro.parsers.ContentLoaderContextMock
import org.skepsun.kototoro.parsers.model.ContentListFilter
import org.skepsun.kototoro.parsers.model.SortOrder
import org.skepsun.kototoro.parsers.util.await

/**
 * 在线验证 51吃瓜 的浏览/详情/阅读链路。
 *
 * 回归背景：
 * - 列表页 article 混有广告条目(ad-item, 外链)
 * - 详情页描述曾被广告/导航/上下篇/评论区污染
 * - 视频提取曾把贴片广告 GIF 与 H.265 编码版误识别为可播视频
 *
 * 启用方式: 设置环境变量 CG51_INTEGRATION_TEST=1 后运行；
 * 代理（如有）通过 HTTPS_PROXY/HTTP_PROXY/ALL_PROXY 环境变量生效。
 */
@EnabledIfEnvironmentVariable(named = "CG51_INTEGRATION_TEST", matches = "1")
class Cg51ParserIntegrationTest {

    private val parser = Cg51(ContentLoaderContextMock)

    @Test
    fun `list detail and pages`() = runBlocking {
        // 1. 列表: 不含广告条目(全部指向站内 /archives/)
        val list = parser.getListPage(1, SortOrder.NEWEST, ContentListFilter())
        assertTrue(list.size >= 10, "列表过少: ${list.size}")
        list.forEach {
            assertTrue(it.url.contains("/archives/"), "列表混入非文章链接: ${it.url}")
        }

        // 1a. 封面: 站点图片为 AES 加密字节流, 必须解密为本地临时文件(file://)才能显示
        val withCover = list.filter { it.coverUrl != null }
        assertTrue(withCover.isNotEmpty(), "列表无任何封面")
        withCover.take(3).forEach {
            val coverUrl = it.coverUrl!!
            assertTrue(
                coverUrl.startsWith("file://") || coverUrl.startsWith("data:"),
                "封面未解密(仍是远程加密 URL): $coverUrl",
            )
            if (coverUrl.startsWith("file://")) {
                val bytes = java.io.File(coverUrl.removePrefix("file://")).readBytes()
                assertTrue(bytes.size > 1000, "封面文件过小: ${bytes.size}")
                assertEquals(0xFF, bytes[0].toInt() and 0xFF, "JPEG 魔数不符(解密失败): $coverUrl")
                assertEquals(0xD8, bytes[1].toInt() and 0xFF, "JPEG 魔数不符(解密失败): $coverUrl")
            }
        }

        // 2. 详情: 描述不包含常见噪音词; 章节为视频或图集
        val detail = parser.getDetails(list.first())
        val desc = detail.description.orEmpty()
        assertFalse(desc.contains("上一篇"), "上下篇混入描述")
        assertFalse(desc.contains("下一篇"), "上下篇混入描述")
        assertFalse(desc.contains("下载APP"), "下载按钮混入描述")
        assertFalse(desc.contains("热门推荐"), "推荐区混入描述")

        val chapters = detail.chapters.orEmpty()
        assertTrue(chapters.isNotEmpty(), "章节为空")

        // 3. 阅读页: 视频章节地址可访问(2xx/3xx); 数量与章节一致
        val chapter = chapters.first()
        val pages = parser.getPages(chapter)
        assertTrue(pages.isNotEmpty(), "阅读页为空")

        if (chapter.url.contains("#video_index=")) {
            assertEquals(1, pages.size, "视频章节应只有一个地址")
        }

        val pageUrl = parser.getPageUrl(pages.first())
        assertTrue(pageUrl.startsWith("http"), "阅读地址非绝对: $pageUrl")
        val response = ContentLoaderContextMock.httpClient.newCall(
            Request.Builder().url(pageUrl).head().build(),
        ).await()
        response.use {
            assertTrue(it.code in 200..399, "阅读地址不可访问: HTTP ${it.code} $pageUrl")
        }
    }
}
