package com.bertholucci.core.extensions

import android.app.Activity
import android.content.Context
import android.view.View
import android.widget.ImageView
import androidx.annotation.StringRes
import androidx.core.content.ContextCompat
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import coil.load
import com.bertholucci.core.BuildConfig
import com.bertholucci.core.R
import com.google.android.material.snackbar.Snackbar

fun ImageView.loadFromUrl(image: String?) {
    this.load(BuildConfig.BASE_IMAGE_URL.plus(image)) {
        crossfade(true)
        placeholder(R.drawable.core_img_default)
    }
}

fun Activity.showSnack(@StringRes resId: Int) {
    Snackbar.make(getView(), resId, Snackbar.LENGTH_SHORT).show()
}

private fun Activity.getView(): View = findViewById(android.R.id.content)

fun SwipeRefreshLayout.setColor(context: Context?, color: Int = R.color.colorGreen) {
    context?.run { setColorSchemeColors(ContextCompat.getColor(this, color)) }
}