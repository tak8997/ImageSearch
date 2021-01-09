package com.github.tak8997.imagesearch.presentation

import android.os.Bundle
import android.view.View
import androidx.core.widget.doAfterTextChanged
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.observe
import androidx.recyclerview.widget.LinearLayoutManager
import com.github.tak8997.imagesearch.R
import com.github.tak8997.imagesearch.data.model.ImageItem
import com.github.tak8997.imagesearch.databinding.ActivitySearchBinding
import com.github.tak8997.imagesearch.presentation.item.ImageAdapter
import dagger.android.support.DaggerAppCompatActivity
import kotlinx.android.synthetic.main.activity_search.*
import javax.inject.Inject

class ImageSearchActivity : DaggerAppCompatActivity() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private lateinit var viewModel: ImageSearchViewModel
    private lateinit var binding: ActivitySearchBinding

    private val imageAdapter = ImageAdapter()
    private val filterDialog by lazy {
        FiltersBottomSheetDialog.newInstance(viewModel)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(this, viewModelFactory).get(ImageSearchViewModel::class.java)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_search)

        bindViewModel()

        setupFilters()
        setupSearch()
        setupRecycler()
    }

    private fun bindViewModel() {
        with(viewModel) {
            searchResult.observe(this@ImageSearchActivity) { result ->
                when (result) {
                    is Result.Success -> {
                        showImages(result.data)
                        binding.progress.visibility = View.INVISIBLE
                    }
                    is Result.Failure -> {
                        binding.progress.visibility = View.INVISIBLE
                    }
                    is Result.InProgress -> {
                        binding.progress.visibility = View.VISIBLE
                    }
                }
            }
            filterResult.observe(this@ImageSearchActivity) { images ->
                dismissIfDialogShowing()
                showImages(images)
            }
            filters.observe(this@ImageSearchActivity) { filters ->
                if (filters.isNullOrEmpty().not()) {
                    binding.btnFilter.visibility = View.VISIBLE
                }
            }
        }
    }

    private fun showImages(images: List<ImageItem>) {
        imageAdapter.submitList(images)
    }

    private fun dismissIfDialogShowing() {
        if (filterDialog.isVisible) {
            filterDialog.dismiss()
        }
    }

    private fun setupFilters() {
        binding.btnFilter.setOnClickListener {
            filterDialog.show(supportFragmentManager, FiltersBottomSheetDialog.TAG)
        }
    }

    private fun setupSearch() {
        binding.keywordSearch.doAfterTextChanged {
            val query = it?.toString()
            if (query.isNullOrEmpty()) {
                imageAdapter.submitList(listOf())
                return@doAfterTextChanged
            }

            viewModel.searchImage(query)
        }
    }

    private fun setupRecycler() {
        with(imageRecycler) {
            layoutManager = LinearLayoutManager(this@ImageSearchActivity)
            adapter = imageAdapter
            setHasFixedSize(true)
        }
    }
}
