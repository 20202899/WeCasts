package com.carlosscotus.engine

import com.carlosscotus.engine.model.Item
import com.carlosscotus.engine.model.Podcast
import org.jsoup.Jsoup

object Engine {
    suspend fun parsePodcasts(value: String): List<Podcast> {
        val home = Jsoup.parse(value).selectFirst(".z9w95e")
        val divs = home.children()
        return divs.mapIndexed { i, e ->
            val more = e.children()
            if (i == 0) {
                Podcast(
                    title = more.first().text(),
                    casts = more.last().select(".uwjJFd").map {
                        val item = it.children()
                        val header = item.first().children()
                        val titles = header.last().children()
                        val messages = item[1].children()
                        Item(
                            title = titles.first().text(),
                            subTitle = titles.last().text(),
                            image = header.first().selectFirst("img").attr("src"),
                            contentTitle = messages.first().text(),
                            contentMessage = messages.last().text(),
                            timeStamp = item.last().selectFirst(".gUJ0Wc").text(),
                            url = it.selectFirst("a").attr("href"),
                            url_to_mp3 = it.allElements.eachAttr("jsdata")
                                .first()
                                .split(";")
                                .find { f -> f.startsWith("http") } ?: String()
                        )
                    }.toMutableList()
                )
            } else {
                Podcast(
                    title = more.first().text(),
                    casts = more.last().select(".c9x52d").map {
                        Item(
                            title = it.selectFirst(".eWeGpe").text(),
                            subTitle = it.selectFirst(".yFWEIe").text(),
                            image = it.selectFirst("img").attr("src"),
                            url = it.attr("href").toString()
                        )
                    }.toMutableList()
                )
            }
        }
    }
}