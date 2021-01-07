package com.github.tak8997.imagesearch.ui

import android.os.Bundle
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.github.tak8997.imagesearch.R
import com.github.tak8997.imagesearch.databinding.ActivitySearchBinding
import com.github.tak8997.imagesearch.ui.image.ImageAdapter
import dagger.android.support.DaggerAppCompatActivity
import kotlinx.android.synthetic.main.activity_search.*
import javax.inject.Inject

class ImageSearchActivity : DaggerAppCompatActivity() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private lateinit var viewModel: ImageSearchViewModel
    private lateinit var binding: ActivitySearchBinding

    private val imageAdapter = ImageAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(this, viewModelFactory).get(ImageSearchViewModel::class.java)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_search)
        binding.viewModel = viewModel

        bindViewModel()
        setupRecycler()
    }

    private fun bindViewModel() {
        with(viewModel) {
            pages.observe(this@ImageSearchActivity, Observer {
                imageAdapter.submitList(it)
            })
            networkState.observe(this@ImageSearchActivity, Observer {
                Toast.makeText(this@ImageSearchActivity, it.msg, Toast.LENGTH_SHORT).show()
            })
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
