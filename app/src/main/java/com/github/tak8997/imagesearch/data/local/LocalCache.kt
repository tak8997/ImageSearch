package com.github.tak8997.imagesearch.data.local

import com.github.tak8997.imagesearch.data.model.ImageItem
import io.reactivex.Single

interface LocalCache {

    fun saveImages(images: List<ImageItem>)
    fun getSavedImages(): Single<List<ImageItem>>
}

