package com.github.tak8997.imagesearch.ui

import android.text.Editable
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations.switchMap
import androidx.lifecycle.ViewModel
import com.github.tak8997.imagesearch.data.model.ImageItem
import com.github.tak8997.imagesearch.data.repository.AppRepository
import com.github.tak8997.imagesearch.data.repository.Listing
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.addTo
import io.reactivex.subjects.PublishSubject
import java.util.concurrent.TimeUnit
import javax.inject.Inject

class ImageSearchViewModel @Inject constructor(
    private val repository: AppRepository,
    private val disposables: CompositeDisposable
): ViewModel() {

    private val pageResult = MutableLiveData<Listing<ImageItem>>()
    private val query = PublishSubject.create<String>()

    val pages = switchMap(pageResult) { it.pages }
    val networkState = switchMap(pageResult) { it.networkState }

    init {
        query
            .debounce(1000, TimeUnit.MILLISECONDS)
            .filter { it.isNotEmpty() }
            .flatMapSingle { repository.search(it) }
            .subscribe(pageResult::postValue)
            .addTo(disposables)
    }

    override fun onCleared() {
        super.onCleared()
        disposables.clear()
    }

    fun onSearchAfterTextChanged(keyword: Editable) {
        query.onNext(keyword.toString())
    }
}