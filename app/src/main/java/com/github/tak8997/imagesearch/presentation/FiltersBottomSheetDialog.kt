package com.github.tak8997.imagesearch.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.observe
import androidx.recyclerview.widget.LinearLayoutManager
import com.github.tak8997.imagesearch.databinding.DialogFiltersBinding
import com.github.tak8997.imagesearch.presentation.item.FilterAdapter
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

internal class FiltersBottomSheetDialog : BottomSheetDialogFragment() {

    companion object {
        const val TAG: String = "FiltersBottomSheetDialog"

        fun newInstance(viewModel: ImageSearchViewModel): FiltersBottomSheetDialog {
            return FiltersBottomSheetDialog().apply {
                this.viewModel = viewModel
            }
        }
    }

    private lateinit var binding: DialogFiltersBinding

    private var viewModel: ImageSearchViewModel? = null
    private val filterAdapter by lazy {
        FilterAdapter(viewModel)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DialogFiltersBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRecycler()
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel?.run {
            filters.observe(viewLifecycleOwner) {
                filterAdapter.setItems(it)
            }
        }
    }

    private fun setupRecycler() {
        with(binding.rvFilters) {
            layoutManager = LinearLayoutManager(context ?: return@with)
            adapter = filterAdapter
        }
    }
}