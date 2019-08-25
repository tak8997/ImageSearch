package com.github.tak8997.imagesearch.di.module

import androidx.lifecycle.ViewModel
import com.github.tak8997.imagesearch.di.key.ViewModelKey
import com.github.tak8997.imagesearch.ui.MainViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
interface MainModule {

    @Binds
    @IntoMap
    @ViewModelKey(MainViewModel::class)
    fun bindsViewModel(viewModel: MainViewModel): ViewModel
}
