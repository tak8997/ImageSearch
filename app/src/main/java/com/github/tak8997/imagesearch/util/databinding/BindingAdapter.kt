package com.github.tak8997.imagesearch.util.databinding

import android.util.Log
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.request.RequestOptions
import com.github.tak8997.imagesearch.data.model.ImageItem

object BindingAdapter {

    @JvmStatic
    @BindingAdapter("srcCompat")
    fun setSrcCompat(imageView: ImageView, item: ImageItem?) {
        Log.d("MY_LOG", "width : ${imageView.width}")
        item?.let {
            Glide.with(imageView)
                .load(it.thumbnailUrl)
                .override(imageView.width, it.height)
                .into(imageView)
        }
    }
}