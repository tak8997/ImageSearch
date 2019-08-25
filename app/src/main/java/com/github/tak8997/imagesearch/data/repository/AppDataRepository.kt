package com.github.tak8997.imagesearch.data.repository

import androidx.paging.PagedList
import androidx.paging.RxPagedListBuilder
import com.github.tak8997.imagesearch.data.ApiService
import com.github.tak8997.imagesearch.data.model.ImageItem
import com.github.tak8997.imagesearch.data.repository.paging.SearchDataSourceFactory
import io.reactivex.Observable
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject

class AppDataRepository @Inject constructor(
    private val apiService: ApiService,
    private val disposables: CompositeDisposable
): AppRepository {

    override fun search(keyword: String): Observable<PagedList<ImageItem>> {
        val dataSourceFactory = SearchDataSourceFactory(keyword, apiService, disposables)

        val config = PagedList.Config.Builder()
            .setPageSize(20)
            .setInitialLoadSizeHint(40)
            .setEnablePlaceholders(false)
            .build()

        return RxPagedListBuilder(dataSourceFactory, config).buildObservable()
    }
}