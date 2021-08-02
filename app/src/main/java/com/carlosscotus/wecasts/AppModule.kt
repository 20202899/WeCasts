package com.carlosscotus.wecasts

import androidx.activity.ComponentActivity
import com.carlosscotus.wecasts.data.HomeDataSource
import com.carlosscotus.wecasts.data.HomeDataSourceImpl
import com.carlosscotus.wecasts.data.HomeRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import javax.inject.Singleton

@Module
@InstallIn(ViewModelComponent::class)
object AppModule {
    @Provides
    fun createHomeRepository() = HomeRepository()

    @Provides
    fun createHomeDataResource(homeRepository: HomeRepository): HomeDataSource = HomeDataSourceImpl(
            homeRepository
    )
}