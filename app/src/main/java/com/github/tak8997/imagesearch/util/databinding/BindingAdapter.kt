package com.github.tak8997.imagesearch.util.databinding

import android.content.Context
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.swiperefreshlayout.widget.CircularProgressDrawable
import com.bumptech.glide.Glide
import com.github.tak8997.imagesearch.R
import com.github.tak8997.imagesearch.data.model.ImageItem

object BindingAdapter {

    @JvmStatic
    @BindingAdapter("srcCompat")
    fun setSrcCompat(imageView: ImageView, item: ImageItem?) {
        item?.let {
            Glide.with(imageView)
                .load(it.thumbnailUrl)
                .placeholder(circularProgressDrawable(imageView.context))
                .into(imageView)
        }
    }

    @JvmStatic
    fun circularProgressDrawable(context: Context): CircularProgressDrawable {
        val progress = CircularProgressDrawable(context)
        progress.setColorSchemeColors(R.color.colorAccent)
        progress.strokeWidth = 5f
        progress.centerRadius = 30f
        progress.start()
        return progress
    }
}