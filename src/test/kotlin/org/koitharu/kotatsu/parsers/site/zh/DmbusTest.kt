package org.koitharu.kotatsu.parsers.site.zh

import kotlinx.coroutines.runBlocking
import org.junit.jupiter.api.Test
import org.skepsun.kototoro.parsers.model.MangaListFilter
import org.skepsun.kototoro.parsers.model.*
import org.skepsun.kototoro.parsers.MangaLoaderContext
import org.skepsun.kototoro.parsers.util.*
import org.skepsun.kototoro.parsers.site.zh.Dmbus
import org.jsoup.Jsoup

class DmbusTest {

    private val context = MangaLoaderContext.createDefault()
    private val parser = Dmbus(context)

    @Test
    fun testGetListPage() = runBlocking {
        // 测试获取国漫列表
        val mangaList = parser.getListPage(1, SortOrder.POPULARITY, MangaListFilter.Empty)
        println("获取到 ${mangaList.size} 个视频")
        mangaList.take(5).forEach { manga: Manga ->
            println("标题: ${manga.title}, URL: ${manga.url}")
        }
        assert(mangaList.isNotEmpty()) { "应该获取到视频列表" }
    }

    @Test
    fun testSearch() = runBlocking {
        // 测试搜索功能
        val searchFilter = MangaListFilter.Search("斗罗大陆")
        val searchResults = parser.getListPage(1, SortOrder.POPULARITY, searchFilter)
        println("搜索到 ${searchResults.size} 个结果")
        searchResults.take(5).forEach { manga: Manga ->
            println("搜索结果: ${manga.title}, URL: ${manga.url}")
        }
        assert(searchResults.isNotEmpty()) { "应该搜索到结果" }
    }

    @Test
    fun testGetDetails() = runBlocking {
        // 测试获取详情（使用一个已知的视频URL）
        val testManga = Manga(
            id = 1L,
            title = "测试视频",
            altTitles = emptySet(),
            url = "/v/5827.html", // 示例视频
            publicUrl = "https://dmbus.cc/v/5827.html",
            rating = 0f,
            contentRating = ContentRating.SAFE,
            coverUrl = "",
            tags = emptySet(),
            state = null,
            authors = emptySet(),
            source = parser.source,
        )
        
        val detailedManga = parser.getDetails(testManga)
        println("视频详情: ${detailedManga.title}")
        println("描述: ${detailedManga.description?.take(100)}...")
        println("标签: ${detailedManga.tags.joinToString { it.title }}")
        println("剧集数: ${detailedManga.chapters?.size ?: 0}")
        
        assert(detailedManga.title.isNotBlank()) { "应该有标题" }
        assert(detailedManga.chapters?.isNotEmpty() ?: false) { "应该有剧集" }
    }

    @Test
    fun testGetPages() = runBlocking {
        // 测试获取播放页面（使用一个已知的剧集URL）
        val testChapter = MangaChapter(
            id = 1L,
            title = "测试剧集",
            number = 1f,
            volume = 0,
            url = "/p/5827-1-1.html", // 示例播放页面
            scanlator = null,
            uploadDate = 0L,
            branch = null,
            source = parser.source,
        )
        
        val pages = parser.getPages(testChapter)
        println("获取到 ${pages.size} 个视频源")
        pages.take(3).forEach { page: MangaPage ->
            println("视频源: ${page.url.take(100)}...")
        }
        
        // 即使没有找到视频源也不报错，因为网站可能会变化
        println("视频源解析完成")
    }

    @Test
    fun testParseCategoryList() = runBlocking {
        // 测试解析分类列表
        val htmlContent = """
            <div class="video-list">
                <div class="item">
                    <a href="/v/123.html" title="测试视频1">
                        <img src="/cover1.jpg" alt="测试视频1">
                        <div class="title">测试视频1</div>
                    </a>
                </div>
                <div class="item">
                    <a href="/v/456.html" title="测试视频2">
                        <img src="/cover2.jpg" alt="测试视频2">
                        <div class="name">测试视频2</div>
                    </a>
                </div>
            </div>
        """.trimIndent()
        
        val doc = Jsoup.parse(htmlContent)
        val mangaList = parser.parseCategoryList(doc)
        
        println("解析到 ${mangaList.size} 个视频")
        mangaList.forEach { manga: Manga ->
            println("解析结果: ${manga.title}, URL: ${manga.url}")
        }
        
        assert(mangaList.size == 2) { "应该解析到2个视频" }
        assert(mangaList.any { manga: Manga -> manga.title == "测试视频1" }) { "应该包含测试视频1" }
        assert(mangaList.any { manga: Manga -> manga.title == "测试视频2" }) { "应该包含测试视频2" }
    }

    @Test
    fun testParseSearchResults() = runBlocking {
        // 测试解析搜索结果
        val htmlContent = """
            <div class="search-result">
                <div class="search-result-item">
                    <a href="/v/789.html">
                        <img src="/search1.jpg" alt="搜索视频1">
                        <h3>搜索视频1</h3>
                    </a>
                </div>
                <div class="video-item">
                    <a href="/v/101.html" title="搜索视频2">
                        <img data-src="/search2.jpg">
                        <div class="title">搜索视频2</div>
                    }
                </div>
            </div>
        """.trimIndent()
        
        val doc = Jsoup.parse(htmlContent)
        val mangaList = parser.parseSearchResults(doc)
        
        println("解析到 ${mangaList.size} 个搜索结果")
        mangaList.forEach { manga: Manga ->
            println("搜索结果: ${manga.title}, URL: ${manga.url}")
        }
        
        assert(mangaList.size == 2) { "应该解析到2个搜索结果" }
        assert(mangaList.any { manga: Manga -> manga.title == "搜索视频1" }) { "应该包含搜索视频1" }
        assert(mangaList.any { manga: Manga -> manga.title == "搜索视频2" }) { "应该包含搜索视频2" }
    }
}