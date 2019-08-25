package com.github.tak8997.imagesearch.data.repository.paging

import androidx.paging.DataSource
import com.github.tak8997.imagesearch.data.ApiService
import com.github.tak8997.imagesearch.data.model.ImageItem
import io.reactivex.disposables.CompositeDisposable

class SearchDataSourceFactory(
    private val keyword: String,
    private val apiService: ApiService,
    private val disposables: CompositeDisposable
) : DataSource.Factory<Int, ImageItem>() {

    override fun create(): DataSource<Int, ImageItem> {
        return SearchDataSource(keyword, apiService, disposables)
    }
}