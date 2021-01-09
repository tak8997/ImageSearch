package com.github.tak8997.imagesearch.di

import com.github.tak8997.imagesearch.presentation.ImageSearchActivity
import com.github.tak8997.imagesearch.di.module.ImageSearchModule
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityBindingModule {

    @ContributesAndroidInjector(modules = [ImageSearchModule::class])
    abstract fun mainActivity(): ImageSearchActivity
}
