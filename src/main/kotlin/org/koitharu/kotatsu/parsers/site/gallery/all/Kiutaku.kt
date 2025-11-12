package org.skepsun.kototoro.parsers.site.gallery.all

import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response
import kotlinx.coroutines.*
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock
import org.skepsun.kototoro.parsers.*
import org.skepsun.kototoro.parsers.model.*
import org.skepsun.kototoro.parsers.site.gallery.GalleryParser

@MangaSourceParser("KIUTAKU", "Kiutaku", type = ContentType.OTHER)
internal class Kiutaku(context: MangaLoaderContext) :
    GalleryParser(context, MangaParserSource.KIUTAKU, "kiutaku.com") {

    companion object {
        private val mutex = Mutex()
        private var lastImageRequestTime = 0L
        private val IMAGE_EXTENSIONS = listOf(".jpg", ".jpeg", ".png", ".webp")
    }

    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
        val url = request.url.toString()

        // 处理 kiutaku.com 和 mitaku.net 的图片请求
        if (IMAGE_EXTENSIONS.any { url.endsWith(it, ignoreCase = true) } && 
            (url.contains("kiutaku.com") || url.contains("mitaku.net"))) {
            runBlocking {
                mutex.withLock {
                    val now = System.currentTimeMillis()
                    val wait = 5000L - (now - lastImageRequestTime) // 增加到5秒，应对CloudFlare挑战
                    if (wait > 0) {
                        delay(wait)
                    }
                    lastImageRequestTime = System.currentTimeMillis()
                }
            }
        }

        // 执行请求
        var response = chain.proceed(request)
        
        // 检查是否被CloudFlare拦截
        if (response.code == 403 || response.code == 429 || 
            (response.body?.string()?.contains("cloudflare") == true)) {
            response.close()
            
            // 如果是CloudFlare拦截，等待更长时间后重试
            runBlocking {
                delay(8000L) // 等待8秒应对CloudFlare的5秒挑战
            }
            
            // 重试请求
            response = chain.proceed(request)
        }
        
        return response
    }
}
