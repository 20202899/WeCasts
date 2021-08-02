package com.carlosscotus.wecasts.data

import com.carlosscotus.engine.Engine
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.net.HttpURLConnection
import java.net.URL

class HomeRepository : HomeDataSource {
    private val HOME_URL = "https://podcasts.google.com"

    override suspend fun getPodcasts() = withContext(Dispatchers.IO) {
        val url = URL(HOME_URL)
        (url.openConnection() as? HttpURLConnection)?.run {
            requestMethod = "GET"
            setRequestProperty("Accept-Language", "pt_BR")
            setRequestProperty("Content-Type", "application/x-www-form-urlencoded;charset=utf-8")
            Engine.parsePodcasts(String(inputStream.readBytes()))
        } ?: listOf()
    }
}