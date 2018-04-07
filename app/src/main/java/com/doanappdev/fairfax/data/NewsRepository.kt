package com.doanappdev.fairfax.data

import com.doanappdev.fairfax.data.models.NewsResponse
import io.reactivex.Observable

interface NewsRepository {
    fun getNewsArticles() : Observable<NewsResponse>
}
