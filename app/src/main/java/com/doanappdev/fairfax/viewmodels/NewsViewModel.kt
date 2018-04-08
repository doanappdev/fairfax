package com.doanappdev.fairfax.viewmodels

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import com.doanappdev.fairfax.base.ItemView
import com.doanappdev.fairfax.data.NewsRepository
import com.doanappdev.fairfax.data.models.NewsItem
import com.doanappdev.fairfax.data.models.NewsResponse
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.merge
import io.reactivex.rxkotlin.plusAssign
import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.error
import org.jetbrains.anko.info


class NewsViewModel: ViewModel(), AnkoLogger {

    val newsItems = MutableLiveData<MutableList<ItemView>>()
    val isShowProgressBar = MutableLiveData<Boolean>()
    val itemClick = MutableLiveData<String>()

    private val disposables: CompositeDisposable = CompositeDisposable()

    fun getNewsArticles(repository: NewsRepository) {
        isShowProgressBar.value = true
        repository.getNewsArticles()
                .subscribe ({
                    it?.let { onSuccess(it) }
                }, {
                    // handle network error, for now just print error msg
                    error(it.message)
                    isShowProgressBar.value = false
                })
    }

    fun onSuccess(newsResponse: NewsResponse) {
        val items = mutableListOf<ItemView>()
        newsResponse.assets.map { items.add(NewsItem(it)) }
        registerClickEvents(items)
        sortNewsItems(items)
    }

    /**
     * sort list by descending order using Kotlin's standard library
     * method 'sortedWith'
     */
    fun sortNewsItems(items: MutableList<ItemView>) {
        val sortedNewsItems = mutableListOf<ItemView>()
        items.sortedWith(compareByDescending {
            it as NewsItem
            it.asset.date
        }).forEach {
            sortedNewsItems.add(it)
        }

        // these objects are observable so we
        // can observe when their when values are
        // changed and update UI
        newsItems.value = sortedNewsItems
        isShowProgressBar.value = false
    }

    /**
     * filter list for instance of NewsItem then map click events
     * for each items and merge them into a single event
     */
    fun registerClickEvents(items: MutableList<ItemView>) {
        val clicks = items.filterIsInstance<NewsItem>()
                .map { it.clickSubject.hide() }
                .merge()

        disposables += clicks.subscribe {
            it?.let { itemClick.value = it }
        }
    }

    /**
     * clear disposable to prevent memory leaks
     */
    fun clear() {
        disposables.clear()
    }
}