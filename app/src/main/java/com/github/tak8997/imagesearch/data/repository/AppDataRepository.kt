package com.github.tak8997.imagesearch.data.repository

import androidx.lifecycle.Transformations
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.github.tak8997.imagesearch.data.ApiService
import com.github.tak8997.imagesearch.data.model.ImageItem
import com.github.tak8997.imagesearch.data.repository.paging.SearchDataSourceFactory
import io.reactivex.Single
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject

class AppDataRepository @Inject constructor(
    private val apiService: ApiService,
    private val disposables: CompositeDisposable
): AppRepository {

    override fun search(keyword: String): Single<Listing<ImageItem>> {
        val dataSourceFactory = SearchDataSourceFactory(keyword, apiService, disposables)

        val config = PagedList.Config.Builder()
            .setPageSize(20)
            .setInitialLoadSizeHint(40)
            .setEnablePlaceholders(false)
            .build()

        val networkError = Transformations.switchMap(dataSourceFactory.sourceLiveData) {
            it.networkError
        }

        return Single.just(Listing(
            LivePagedListBuilder(dataSourceFactory, config).build(),
            networkError
        ))
    }
}