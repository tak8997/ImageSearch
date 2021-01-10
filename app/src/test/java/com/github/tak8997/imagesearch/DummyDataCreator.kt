package com.github.tak8997.imagesearch

import com.github.tak8997.imagesearch.data.model.ImageItem
import com.github.tak8997.imagesearch.data.model.ImageSearchResponse
import com.github.tak8997.imagesearch.data.model.Meta

fun getDummyImageSearchResponse(): ImageSearchResponse {
    return ImageSearchResponse(
        Meta(2, 2, true),
        getDummyImageItems()
    )
}

fun getDummyImageItems(): List<ImageItem> {
    return listOf(
        ImageItem("news", "http://thumbkakao.com", "http://kakao.com", "ryan"),
        ImageItem("cafe", "http://thumbkakao1.com", "http://kakao1.com", "muzi")
    )
}

fun getDummyFilteredImageItems(): List<ImageItem> {
    return listOf(
        ImageItem("cafe", "http://thumbkakao.com", "http://kakao.com", "ryan"),
        ImageItem("cafe", "http://thumbkakao1.com", "http://kakao1.com", "muzi")
    )
}
