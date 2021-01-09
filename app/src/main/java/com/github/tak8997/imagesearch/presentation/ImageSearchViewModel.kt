package com.github.tak8997.imagesearch.presentation

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.map
import com.github.tak8997.imagesearch.data.model.ImageItem
import com.github.tak8997.imagesearch.data.model.getFilters
import com.github.tak8997.imagesearch.data.repository.AppRepository
import com.github.tak8997.imagesearch.util.SchedulerProvider
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.addTo
import javax.inject.Inject

class ImageSearchViewModel @Inject constructor(
    private val repository: AppRepository,
    private val schedulerProvider: SchedulerProvider
) : ViewModel() {

    private val disposables = CompositeDisposable()

    val filterResult by lazy {
        MutableLiveData<List<ImageItem>>()
    }
    val searchResult = MutableLiveData<Result<List<ImageItem>>>()
    val filters = searchResult.map {
        it.data
            ?.getFilters()
            ?.distinct()
    }

    fun searchImage(query: String) {
        repository.search(query)
            .toResult(schedulerProvider)
            .subscribe(searchResult::setValue)
            .addTo(disposables)
    }

    fun onClickFilter(filter: String) {
        repository.getFilteredImages(filter)
            .subscribe(filterResult::setValue)
            .addTo(disposables)
    }

    override fun onCleared() {
        super.onCleared()
        disposables.clear()
    }
}