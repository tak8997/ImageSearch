package com.github.tak8997.imagesearch.data.repository

import com.github.tak8997.imagesearch.data.local.LocalCache
import com.github.tak8997.imagesearch.data.model.ImageItem
import com.github.tak8997.imagesearch.data.remote.ApiService
import com.github.tak8997.imagesearch.util.SchedulerProvider
import io.reactivex.Single
import javax.inject.Inject

class AppDataRepository @Inject constructor(
    private val apiService: ApiService,
    private val localCache: LocalCache,
    private val schedulerProvider: SchedulerProvider
) : AppRepository {

    override fun search(query: String): Single<List<ImageItem>> {
        return apiService.search(query)
            .map { it.images }
            .doOnSuccess(localCache::saveImages)
            .subscribeOn(schedulerProvider.io())
    }

    override fun getFilteredImages(filter: String): Single<List<ImageItem>> {
        return localCache.getSavedImages()
            .map { images ->
                images.filter { it.filter == filter }
            }
    }
}