package com.github.tak8997.imagesearch.data.repository

import androidx.paging.PagedList
import com.github.tak8997.imagesearch.data.model.ImageItem
import io.reactivex.Observable

interface AppRepository {

    fun search(query: String): Observable<PagedList<ImageItem>>
}