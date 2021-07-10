package com.bertholucci.core.extensions

import android.graphics.drawable.Drawable
import android.view.View
import android.widget.ImageView
import androidx.core.view.isVisible
import com.bertholucci.data.BuildConfig
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.bumptech.glide.request.RequestOptions
import com.bumptech.glide.request.target.Target

fun View.visible() {
    isVisible = true
}

fun View.gone() {
    isVisible = false
}

fun ImageView.loadFromUrl(image: String?): Target<Drawable> {
    return Glide.with(this)
        .load(BuildConfig.BASE_IMAGE_URL.plus(image))
        .transition(DrawableTransitionOptions.withCrossFade())
        .apply(
            RequestOptions()
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .dontAnimate()
                .centerCrop()
                .dontTransform()
        )
        .into(this)
}