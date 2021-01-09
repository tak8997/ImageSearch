package com.github.tak8997.imagesearch.di.module

import androidx.lifecycle.ViewModel
import com.github.tak8997.imagesearch.di.key.ViewModelKey
import com.github.tak8997.imagesearch.presentation.ImageSearchViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
interface ImageSearchModule {

    @Binds
    @IntoMap
    @ViewModelKey(ImageSearchViewModel::class)
    fun bindsImageSearchViewModel(viewModel: ImageSearchViewModel): ViewModel
}
