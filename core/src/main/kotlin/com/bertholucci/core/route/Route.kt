package com.bertholucci.core.route

import android.content.Context
import com.bertholucci.core.extensions.intentForAction

const val ACTION_HOME = "HOME"
const val ACTION_MOVIE = "MOVIE"

const val EXTRA_ID = "EXTRA_ID"

fun Context.intentToHome() = intentForAction(ACTION_HOME)

fun Context.intentToMovie(id: String) = intentForAction(ACTION_MOVIE).apply {
    putExtra(EXTRA_ID, id)
}