package com.github.tak8997.imagesearch.data.repository

import com.github.tak8997.imagesearch.data.model.ImageItem
import io.reactivex.Single

interface AppRepository {

    fun search(query: String): Single<List<ImageItem>>
    fun getFilteredImages(filter: String): Single<List<ImageItem>>
}