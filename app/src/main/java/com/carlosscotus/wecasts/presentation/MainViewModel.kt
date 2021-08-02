package com.carlosscotus.wecasts.presentation

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.carlosscotus.engine.model.Podcast
import com.carlosscotus.wecasts.data.HomeDataSource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
        private val savedStateHandle: SavedStateHandle,
        private val homeDataSource: HomeDataSource
) : ViewModel() {

    val podcastsLiveData: MutableLiveData<List<Podcast>> = MutableLiveData()

    fun getPodcasts() {
        viewModelScope.launch {
            withContext(Dispatchers.Default) { homeDataSource.getPodcasts() }.let {
                podcastsLiveData.value = it.toMutableList()
            }
        }
    }
}