package com.github.tak8997.imagesearch.di.module

import androidx.lifecycle.ViewModel
import com.github.tak8997.imagesearch.di.key.ViewModelKey
import com.github.tak8997.imagesearch.ui.SearchViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
interface MainModule {

    @Binds
    @IntoMap
    @ViewModelKey(SearchViewModel::class)
    fun bindsViewModel(viewModel: SearchViewModel): ViewModel
}
