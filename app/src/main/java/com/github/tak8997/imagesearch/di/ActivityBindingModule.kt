package com.github.tak8997.imagesearch.di

import com.github.tak8997.imagesearch.ui.MainActivity
import com.github.tak8997.imagesearch.di.module.MainModule
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityBindingModule {

    @ContributesAndroidInjector(modules = [MainModule::class])
    abstract fun mainActivity(): MainActivity
}
