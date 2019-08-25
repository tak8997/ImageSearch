package com.github.tak8997.imagesearch.ui

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.paging.PagedList
import com.github.tak8997.imagesearch.data.model.ImageItem
import com.github.tak8997.imagesearch.data.repository.AppRepository
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.addTo
import javax.inject.Inject

class SearchViewModel @Inject constructor(
    private val repository: AppRepository,
    private val disposables: CompositeDisposable
): ViewModel() {

    val images: MutableLiveData<PagedList<ImageItem>> = MutableLiveData()

    fun onSearchTextChanged(keyword: CharSequence, start: Int, before: Int, count: Int) {
        repository
            .search(keyword.toString())
            .subscribe(images::setValue)
            .addTo(disposables)
    }
}