package com.github.tak8997.imagesearch.ui

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations.switchMap
import androidx.lifecycle.ViewModel
import com.github.tak8997.imagesearch.data.model.ImageItem
import com.github.tak8997.imagesearch.data.repository.AppRepository
import com.github.tak8997.imagesearch.data.repository.Listing
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.addTo
import javax.inject.Inject

class SearchViewModel @Inject constructor(
    private val repository: AppRepository,
    private val disposables: CompositeDisposable
): ViewModel() {

    private val pageResult: MutableLiveData<Listing<ImageItem>> = MutableLiveData()

    val pages = switchMap(pageResult) { it.pages }
    val networkState = switchMap(pageResult) { it.networkState }

    override fun onCleared() {
        super.onCleared()
        disposables.clear()
    }

    fun onSearchTextChanged(keyword: CharSequence, start: Int, before: Int, count: Int) {
        repository
            .search(keyword.toString())
            .subscribe(pageResult::setValue)
            .addTo(disposables)
    }
}