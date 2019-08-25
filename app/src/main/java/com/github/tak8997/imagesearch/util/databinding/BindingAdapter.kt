package com.github.tak8997.imagesearch.util.databinding

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.request.RequestOptions

object BindingAdapter {

    @JvmStatic
    @BindingAdapter("srcCompat")
    fun setSrcCompat(imageView: ImageView, url: String?) {
        url?.let {
            Glide.with(imageView)
                .load(it)
                .apply(RequestOptions().transform(CenterCrop()))
                .into(imageView)
        }
    }
}