package com.bertholucci.core.route

import android.content.Context
import com.bertholucci.core.extensions.intentForAction
import com.bertholucci.data.model.MovieType

const val ACTION_HOME = "HOME"
const val ACTION_MOVIE = "MOVIE"
const val ACTION_MOVIE_LIST = "MOVIE_LIST"

const val EXTRA_ID = "EXTRA_ID"
const val EXTRA_MOVIE_TYPE = "EXTRA_MOVIE_TYPE"

fun Context.intentToHome() = intentForAction(ACTION_HOME)

fun Context.intentToMovie(id: String) = intentForAction(ACTION_MOVIE).apply {
    putExtra(EXTRA_ID, id)
}

fun Context.intentToMovieList(type: MovieType) = intentForAction(ACTION_MOVIE_LIST).apply {
    putExtra(EXTRA_MOVIE_TYPE, type.toString())
}