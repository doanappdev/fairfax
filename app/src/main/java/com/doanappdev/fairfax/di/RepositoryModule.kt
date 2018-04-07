package com.doanappdev.fairfax.di

import com.doanappdev.fairfax.data.NewsRepository
import com.doanappdev.fairfax.data.NewsRepositoryImpl
import com.doanappdev.fairfax.service.NewsService
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class RepositoryModule {
    @[Provides Singleton]
    fun provideNewsRepository(service: NewsService) : NewsRepository = NewsRepositoryImpl(service)
}