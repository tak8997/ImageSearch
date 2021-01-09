package com.github.tak8997.imagesearch.util.databinding

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.github.tak8997.imagesearch.data.model.ImageItem

object BindingAdapter {

    @JvmStatic
    @BindingAdapter("srcCompat")
    fun setSrcCompat(imageView: ImageView, image: ImageItem?) {
        image?.let {
            Glide.with(imageView)
                .load(it.thumbnailUrl)
                .into(imageView)
        }
    }
}