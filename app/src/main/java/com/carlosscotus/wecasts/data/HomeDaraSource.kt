package com.carlosscotus.wecasts.data

import com.carlosscotus.engine.model.Podcast

interface HomeDataSource {
    suspend fun getPodcasts() : List<Podcast>
}