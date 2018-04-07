package com.doanappdev.fairfax.service

import com.doanappdev.fairfax.data.models.NewsResponse
import io.reactivex.Observable
import retrofit2.http.GET


interface NewsService {

    @GET("1/coding_test/13ZZQX/full")
    fun getNewsArticles() : Observable<NewsResponse>
}