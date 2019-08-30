package com.github.tak8997.imagesearch.data.repository.paging

import androidx.lifecycle.MutableLiveData
import androidx.paging.PageKeyedDataSource
import com.github.tak8997.imagesearch.data.ApiService
import com.github.tak8997.imagesearch.data.model.ImageItem
import com.github.tak8997.imagesearch.data.repository.NetworkState
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.addTo

class SearchDataSource(
    private val keyword: String,
    private val apiService: ApiService,
    private val disposables: CompositeDisposable
) : PageKeyedDataSource<Int, ImageItem>() {

    val networkError = MutableLiveData<NetworkState>()

    override fun loadInitial(params: LoadInitialParams<Int>, callback: LoadInitialCallback<Int, ImageItem>) {
        apiService
            .search(keyword, 1)
            .subscribe({
                callback.onResult(it.documents, null, 2)
            }, {
                networkError.postValue(NetworkState.error(it.message))
            })
            .addTo(disposables)
    }

    override fun loadAfter(params: LoadParams<Int>, callback: LoadCallback<Int, ImageItem>) {
        apiService
            .search(keyword, params.key)
            .subscribe({
                callback.onResult(it.documents, params.key + 1)
            }, {
                networkError.postValue(NetworkState.error(it.message))
            })
            .addTo(disposables)
    }

    override fun loadBefore(params: LoadParams<Int>, callback: LoadCallback<Int, ImageItem>) {}
}