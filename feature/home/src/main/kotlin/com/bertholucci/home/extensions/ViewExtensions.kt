package com.bertholucci.home.extensions

import android.widget.ImageView
import com.bertholucci.home.R

fun ImageView.isFavorite() {
    this.setImageResource(R.drawable.ic_save)
}

fun ImageView.isNotFavorite() {
    this.setImageResource(R.drawable.ic_save_grey)
}