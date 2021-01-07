package com.github.tak8997.imagesearch.util.databinding

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide

object BindingAdapter {

    @JvmStatic
    @BindingAdapter("srcCompat")
    fun setSrcCompat(imageView: ImageView, imageUrl: String?) {
        imageUrl?.let {
            Glide.with(imageView)
                .load(it)
                .into(imageView)
        }
    }
}