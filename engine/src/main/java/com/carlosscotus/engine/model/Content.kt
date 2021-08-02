package com.carlosscotus.engine.model

import java.io.Serializable

data class Item (
    val title: String = "",
    val subTitle: String = "",
    val image: String = "",
    val contentTitle: String = "",
    val contentMessage: String = "",
    val timeStamp: String = "",
    val url: String = "",
    val url_to_mp3: String = ""
) : Serializable

data class Podcast(
    val title: String = "",
    val casts: MutableList<Item> = mutableListOf()
) : Serializable