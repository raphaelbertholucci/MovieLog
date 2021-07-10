package com.bertholucci.movie.extensions

import android.widget.ImageView
import com.bertholucci.movie.R

fun ImageView.isFavorite() {
    this.setImageResource(R.drawable.movie_ic_heart_filled)
}

fun ImageView.isNotFavorite() {
    this.setImageResource(R.drawable.movie_ic_heart)
}