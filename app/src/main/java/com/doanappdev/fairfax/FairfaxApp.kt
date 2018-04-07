package com.doanappdev.fairfax

import android.app.Application
import com.doanappdev.fairfax.di.*

class FairfaxApp : Application() {
    companion object {
        lateinit var appComponent: ApplicationComponent
    }

    //@Inject lateinit var locationManager: LocationManager

    override fun onCreate() {
        super.onCreate()
        appComponent = DaggerApplicationComponent.builder()
                .application(this)
                .appModule(AppModule(this))
                .networkModule(NetworkModule())
                .repositoryModule(RepositoryModule())
                .build()

        // if we want to inject objects into this class uncomment this
        //appComponent.inject(this)
    }
}
