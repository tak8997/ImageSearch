package com.github.tak8997.imagesearch

import androidx.lifecycle.Observer
import com.github.tak8997.imagesearch.data.model.ImageItem
import com.github.tak8997.imagesearch.data.repository.AppRepository
import com.github.tak8997.imagesearch.presentation.ImageSearchViewModel
import com.github.tak8997.imagesearch.presentation.Result
import com.github.tak8997.imagesearch.util.TestSchedulerProvider
import com.nhaarman.mockito_kotlin.doReturn
import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.verify
import com.nhaarman.mockito_kotlin.whenever
import io.reactivex.Single
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.robolectric.RobolectricTestRunner

@RunWith(RobolectricTestRunner::class)
class ImageSearchViewModelTest {

    @Mock
    private val repository: AppRepository = mock()

    private lateinit var viewModel: ImageSearchViewModel

    @Before
    fun setup() {
        viewModel = ImageSearchViewModel(repository, TestSchedulerProvider())
    }

    @Test
    fun searchImage_Valid_Test() {
        val query = "query"
        val searchResult = getDummyImageItems()
        whenever(repository.search(query)).doReturn(Single.just(searchResult))
        val result: Observer<Result<List<ImageItem>>> = mock()
        viewModel.searchResult.observeForever(result)

        viewModel.searchImage(query)

        verify(repository).search(query)
        verify(result).onChanged(Result.inProgress())
        verify(result).onChanged(Result.success(searchResult))
    }

    @Test
    fun searchImage_Empty_Test() {
        val runtimeException = RuntimeException("test")
        val query = ""
        whenever(repository.search(query)).doReturn(Single.error(runtimeException))
        val result: Observer<Result<List<ImageItem>>> = mock()
        viewModel.searchResult.observeForever(result)

        viewModel.searchImage(query)

        verify(repository).search(query)
        verify(result).onChanged(Result.inProgress())
        verify(result).onChanged(Result.failure(runtimeException.message!!, runtimeException))
    }

    @Test
    fun onClickFilter_Test() {
        val filter = "cafe"
        val filterResult = getDummyFilteredImageItems()
        whenever(repository.getFilteredImages(filter)).doReturn(Single.just(filterResult))
        val result: Observer<List<ImageItem>> = mock()
        viewModel.filterResult.observeForever(result)

        viewModel.onClickFilter(filter)

        verify(result).onChanged(filterResult)
    }
}