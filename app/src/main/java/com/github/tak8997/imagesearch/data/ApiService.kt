package com.github.tak8997.imagesearch.data

import com.github.tak8997.imagesearch.data.model.ImageSearchResponse
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("search/image")
    fun search(
        @Query("query") keyword: String,
        @Query("page") page: Int
    ) : Single<ImageSearchResponse>
}