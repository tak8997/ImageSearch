package com.github.tak8997.imagesearch.ui.image

import androidx.recyclerview.widget.RecyclerView
import com.github.tak8997.imagesearch.data.model.ImageItem
import com.github.tak8997.imagesearch.databinding.ItemImageBinding

class ImageViewHolder(private val binding: ItemImageBinding) : RecyclerView.ViewHolder(binding.root) {

    fun bind(item: ImageItem) {
        binding.item = item
    }
}