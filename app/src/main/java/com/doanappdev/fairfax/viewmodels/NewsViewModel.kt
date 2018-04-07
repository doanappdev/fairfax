package com.doanappdev.fairfax.viewmodels

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import com.doanappdev.fairfax.base.ItemView
import com.doanappdev.fairfax.data.NewsRepository
import com.doanappdev.fairfax.data.models.NewsItem
import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.info

class NewsViewModel: ViewModel(), AnkoLogger {

    val newsItems = MutableLiveData<MutableList<ItemView>>()

    fun getNewsArticles(repository: NewsRepository) {
        repository.getNewsArticles()
                .subscribe {
                    it?.let {
                        info { "id : ${it.id}" }
                        info { "timestamp : ${it.timeStamp}" }

                        val items = mutableListOf<ItemView>()

                        it.assets.map {
                            info { "id : ${it.id}" }
                            info { "headline : ${it.headline}" }
                            items.add(NewsItem(it))
                        }

                        info { "size : ${items.size}" }

                        newsItems.value = items
                    }
                }
    }



}