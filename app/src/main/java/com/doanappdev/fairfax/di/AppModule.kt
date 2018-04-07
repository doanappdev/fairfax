package com.doanappdev.fairfax.di

import android.app.Application
import android.content.Context
import android.location.LocationManager

import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class AppModule(val application: Application) {


    @[Provides Singleton]
    fun provideApplication(): Application {
        return application
    }

    @[Provides Singleton]
    fun provideContext() : Context = application

    @[Provides Singleton]
    fun provideLocationManager(): LocationManager {
        return application.getSystemService(Context.LOCATION_SERVICE) as LocationManager
    }

//    @Provides
//    fun provideTransactionsRepository(service: CBAService) : TransactionRepository = TransactionRepositoryImpl(service)
//
//    @Provides
//    fun provideTransactionsPresenter(repository: TransactionRepository) : TransactionsContract.Presenter = TransactionsPresenter(repository)


}
