package com.carlosscotus.wecasts.data

import com.carlosscotus.engine.model.Podcast
import javax.inject.Inject

class HomeDataSourceImpl @Inject constructor(private val homeRepository: HomeRepository) : HomeDataSource {
    override suspend fun getPodcasts(): List<Podcast> = homeRepository.getPodcasts()
}