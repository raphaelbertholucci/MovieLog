package com.bertholucci.core.extensions

import android.app.Activity
import android.graphics.drawable.Drawable
import android.view.View
import android.widget.ImageView
import androidx.annotation.StringRes
import com.bertholucci.core.R
import com.bertholucci.data.BuildConfig
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.bumptech.glide.request.RequestOptions
import com.bumptech.glide.request.target.Target
import com.google.android.material.snackbar.Snackbar

fun ImageView.loadFromUrl(image: String?): Target<Drawable> {
    return Glide.with(this)
        .load(BuildConfig.BASE_IMAGE_URL.plus(image))
        .placeholder(R.drawable.core_img_default)
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

fun Activity.showSnack(@StringRes resId: Int) {
    Snackbar.make(getView(), resId, Snackbar.LENGTH_SHORT).show()
}

private fun Activity.getView(): View = findViewById(android.R.id.content)