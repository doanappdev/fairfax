package com.doanappdev.fairfax.data

import com.doanappdev.fairfax.data.models.NewsResponse
import com.doanappdev.fairfax.service.NewsService
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class NewsRepositoryImpl(private val newsService: NewsService) : NewsRepository {
    override fun getNewsArticles(): Observable<NewsResponse> {
        return newsService.getNewsArticles()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
    }
}