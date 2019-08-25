package com.github.tak8997.imagesearch.di

import android.content.Context
import androidx.lifecycle.ViewModelProvider
import com.github.tak8997.imagesearch.BaseViewModelFactory
import com.github.tak8997.imagesearch.data.repository.AppDataRepository
import com.github.tak8997.imagesearch.data.repository.AppRepository
import com.github.tak8997.imagesearch.di.qualifier.App
import com.github.tak8997.imagesearch.util.AppSchedulerProvider
import com.github.tak8997.imagesearch.util.SchedulerProvider
import dagger.Binds
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module(includes = [AppModule.ProvideModule::class])
interface AppModule {
    @Module
    class ProvideModule {

        @Singleton
        @Provides
        @App
        fun provideContext(): Context = com.github.tak8997.imagesearch.App.instance
    }

    @Binds
    @Singleton
    fun bindsWhistleRepository(repository: AppDataRepository): AppRepository

    @Binds
    @Singleton
    fun bindSchedulerProvider(schedulerProvider: AppSchedulerProvider): SchedulerProvider

    @Binds
    fun bindsViewModelFactory(viewModelFactory: BaseViewModelFactory): ViewModelProvider.Factory
}