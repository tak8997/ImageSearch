package com.github.tak8997.imagesearch

import com.github.tak8997.imagesearch.data.local.LocalCache
import com.github.tak8997.imagesearch.data.local.MemoryLocalCache
import com.github.tak8997.imagesearch.data.model.ImageItem
import com.github.tak8997.imagesearch.data.remote.ApiService
import com.github.tak8997.imagesearch.data.repository.AppDataRepository
import com.github.tak8997.imagesearch.data.repository.AppRepository
import com.github.tak8997.imagesearch.util.TestSchedulerProvider
import com.nhaarman.mockito_kotlin.doReturn
import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.whenever
import io.reactivex.Single
import io.reactivex.observers.TestObserver
import org.junit.Before
import org.junit.Test
import org.mockito.Mock

internal class AppDataRepositoryTest {

    @Mock
    private val apiService: ApiService = mock()

    private lateinit var localCache: LocalCache
    private lateinit var repository: AppRepository

    @Before
    fun setup() {
        localCache = MemoryLocalCache()
        repository = AppDataRepository(apiService, localCache, TestSchedulerProvider())
    }

    @Test
    fun searh_Test() {
        val query = "query"
        whenever(apiService.search(query)).doReturn(Single.just(getDummyImageSearchResponse()))

        repository.search(query)
            .test()
            .assertComplete()

        localCache.getSavedImages()
            .test()
            .assertResult(getDummyImageItems())
    }

    @Test
    fun getFilteredImages_Test() {
        val filter = "cafe"
        localCache.saveImages(getDummyImageItems())

        val result = repository.getFilteredImages(filter)
        val testObserver = TestObserver.create<List<ImageItem>>()
        result.subscribe(testObserver)

        testObserver.assertResult(
            listOf(
                ImageItem(
                    "cafe",
                    "http://thumbkakao1.com",
                    "http://kakao1.com",
                    "muzi"
                )
            )
        )
    }
}