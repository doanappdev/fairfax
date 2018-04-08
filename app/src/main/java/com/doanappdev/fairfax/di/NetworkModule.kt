package com.doanappdev.fairfax.di

import com.doanappdev.fairfax.service.NewsService
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
class NetworkModule {

    val BASE_URL = "https://bruce-v2-mob.fairfaxmedia.com.au/"

    @[Provides Singleton]
    fun provideRetrofitBuilder() : Retrofit.Builder =
         Retrofit.Builder()
                 .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                 .addConverterFactory(GsonConverterFactory.create())

    @[Provides Singleton]
    fun provideNewsService(builder: Retrofit.Builder) : NewsService = builder
            .baseUrl(BASE_URL)
            .build()
            .create(NewsService::class.java)

}
