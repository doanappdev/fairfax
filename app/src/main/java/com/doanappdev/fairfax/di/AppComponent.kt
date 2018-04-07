package com.doanappdev.fairfax.di

import com.doanappdev.fairfax.FairfaxApp
import com.doanappdev.fairfax.ui.MainActivity
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [
    AppModule::class,
    NetworkModule::class,
    RepositoryModule::class]
)
interface ApplicationComponent {
    fun inject(mainActivity: MainActivity)


    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: FairfaxApp) : Builder
        fun appModule(appModule: AppModule) : Builder
        fun networkModule(networkModule: NetworkModule) : Builder
        fun repositoryModule(repositoryModule: RepositoryModule) : Builder
        fun build() : ApplicationComponent
    }

    //fun getCBAService() : CBAService
}
