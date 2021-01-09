package com.github.tak8997.imagesearch.presentation.item

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.github.tak8997.imagesearch.databinding.ItemFilterBinding
import com.github.tak8997.imagesearch.presentation.ImageSearchViewModel

internal class FilterAdapter(
    private val viewModel: ImageSearchViewModel?
) : RecyclerView.Adapter<FilterAdapter.ViewHolder>() {

    private val filters = mutableListOf<String>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemFilterBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return ViewHolder(binding).apply {
            itemView.setOnClickListener {
                if (adapterPosition == RecyclerView.NO_POSITION) {
                    return@setOnClickListener
                }

                viewModel?.onClickFilter(filters[adapterPosition])
            }
        }
    }

    override fun getItemCount(): Int {
        return filters.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(filters[position])
    }

    fun setItems(filters: List<String>?) {
        this.filters.clear()
        this.filters.addAll(filters ?: listOf())
        notifyDataSetChanged()
    }

    class ViewHolder(
        private val binding: ItemFilterBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(filter: String) {
            binding.filter = filter
            binding.executePendingBindings()
        }
    }
}