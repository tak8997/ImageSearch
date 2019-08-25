package com.github.tak8997.imagesearch.ui

import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.github.tak8997.imagesearch.BaseActivity
import com.github.tak8997.imagesearch.R
import com.github.tak8997.imagesearch.databinding.ActivitySearchBinding
import com.github.tak8997.imagesearch.ui.image.ImageAdapter
import kotlinx.android.synthetic.main.activity_search.*

class SearchActivity : BaseActivity<ActivitySearchBinding, SearchViewModel>() {

    private val imageAdapter by lazy {
        ImageAdapter()
    }

    override fun getModelClass(): Class<SearchViewModel> = SearchViewModel::class.java
    override fun getLayoutRes(): Int = R.layout.activity_search

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.run {
            pages.observe(this@SearchActivity, Observer {
                imageAdapter.submitList(it)
            })
            networkState.observe(this@SearchActivity, Observer {
                Toast.makeText(this@SearchActivity, it.msg, Toast.LENGTH_SHORT).show()
            })
        }

        setupRecycler()
    }

    private fun setupRecycler() {
        with(imageRecycler) {
            layoutManager = LinearLayoutManager(this@SearchActivity)
            adapter = imageAdapter
        }
    }
}
