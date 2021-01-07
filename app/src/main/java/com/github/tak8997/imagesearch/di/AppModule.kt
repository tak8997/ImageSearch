package com.github.tak8997.imagesearch.di

import android.content.Context
import androidx.lifecycle.ViewModelProvider
import com.github.tak8997.imagesearch.App
import com.github.tak8997.imagesearch.BaseViewModelFactory
import com.github.tak8997.imagesearch.data.repository.AppDataRepository
import com.github.tak8997.imagesearch.data.repository.AppRepository
import com.github.tak8997.imagesearch.util.AppSchedulerProvider
import com.github.tak8997.imagesearch.util.SchedulerProvider
import dagger.Binds
import dagger.Module
import dagger.Provides
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Singleton

@Module(includes = [AppModule.ProvideModule::class])
interface AppModule {
    @Module
    class ProvideModule {

        @Singleton
        @Provides
        fun provideContext(): Context = App.instance

        @Provides
        @Singleton
        fun provideCompositeDisposable(): CompositeDisposable {
            return CompositeDisposable()
        }
    }

    @Binds
    @Singleton
    fun bindsAppRepository(repository: AppDataRepository): AppRepository

    @Binds
    @Singleton
    fun bindsSchedulerProvider(schedulerProvider: AppSchedulerProvider): SchedulerProvider

    @Binds
    fun bindsViewModelFactory(viewModelFactory: BaseViewModelFactory): ViewModelProvider.Factory
}