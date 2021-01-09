package com.github.tak8997.imagesearch.data.model

import com.google.gson.annotations.SerializedName

data class ImageSearchResponse(
    val meta: Meta,
    @SerializedName("documents")
    val images: List<ImageItem>
)

data class Meta(
    @SerializedName("total_count") val totalCount: Int,
    @SerializedName("pageable_count") val pageableCount: Int,
    @SerializedName("is_end") val isEnd: Boolean
)

data class ImageItem(
    @SerializedName("collection") val filter: String,
    @SerializedName("thumbnail_url") val thumbnailUrl: String,
    @SerializedName("image_url") val imageUrl: String,
    @SerializedName("display_sitename") val title: String
)

fun List<ImageItem>.getFilters(): List<String> {
    return map { it.filter }
}