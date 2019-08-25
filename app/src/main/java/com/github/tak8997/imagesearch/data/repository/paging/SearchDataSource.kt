package com.github.tak8997.imagesearch.data.repository.paging

import android.util.Log
import androidx.paging.PageKeyedDataSource
import com.github.tak8997.imagesearch.data.ApiService
import com.github.tak8997.imagesearch.data.model.ImageItem
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.addTo

class SearchDataSource(
    private val keyword: String,
    private val apiService: ApiService,
    private val disposables: CompositeDisposable
) : PageKeyedDataSource<Int, ImageItem>() {

    override fun loadInitial(params: LoadInitialParams<Int>, callback: LoadInitialCallback<Int, ImageItem>) {
        Log.d("MY_LOG", "loadInitial : ${params.requestedLoadSize}")
        apiService
            .search(keyword, params.requestedLoadSize)
            .subscribe({
                callback.onResult(it.documents, null, 2)
            }, { it.printStackTrace() })
            .addTo(disposables)
    }

    override fun loadAfter(params: LoadParams<Int>, callback: LoadCallback<Int, ImageItem>) {
        Log.d("MY_LOG", "loadAfter : ${params.requestedLoadSize}, ${params.key}}")
        apiService
            .search(keyword, params.key)
            .subscribe({
                callback.onResult(it.documents, params.requestedLoadSize)
            }, { it.printStackTrace() })
            .addTo(disposables)
    }

    override fun loadBefore(params: LoadParams<Int>, callback: LoadCallback<Int, ImageItem>) {}
}