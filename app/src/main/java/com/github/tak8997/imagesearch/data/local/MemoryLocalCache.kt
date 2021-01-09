package com.github.tak8997.imagesearch.data.local

import com.github.tak8997.imagesearch.data.model.ImageItem
import io.reactivex.Single
import javax.inject.Inject

class MemoryLocalCache @Inject constructor() : LocalCache {

    private val images = mutableListOf<ImageItem>()

    override fun saveImages(images: List<ImageItem>) {
        this.images.clear()
        this.images.addAll(images)
    }

    override fun getSavedImages(): Single<List<ImageItem>> {
        return Single.defer {
            Single.just(images)
        }
    }
}