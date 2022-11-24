package com.interview.app

import androidx.appcompat.widget.AppCompatImageView
import androidx.databinding.BindingAdapter
import coil.imageLoader
import coil.request.ImageRequest


@BindingAdapter("imageUrl")
fun loadImage(imageView: AppCompatImageView?, url: Any?) {
    imageView?.apply {
        url?.let { url ->
            val request = ImageRequest.Builder(context)
                .data(url)
                .target(this)
                .build()
            context.imageLoader.enqueue(request)
        }
    }
}