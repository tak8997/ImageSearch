package com.github.tak8997.imagesearch.data.model

import com.google.gson.annotations.SerializedName

data class ImageSearchResponse(
    val meta: Meta,
    val documents: List<ImageItem>
)

data class Meta(
    @SerializedName("total_count") val totalCount: Int,
    @SerializedName("pageable_count") val pageableCount: Int,
    @SerializedName("is_end") val isEnd: Boolean
)

data class ImageItem(
    @SerializedName("thumbnail_url") val thumbnailUrl: String,
    @SerializedName("image_url") val imageUrl: String,
    val width: Int,
    val height: Int,
    @SerializedName("display_sitename") val title: String
)