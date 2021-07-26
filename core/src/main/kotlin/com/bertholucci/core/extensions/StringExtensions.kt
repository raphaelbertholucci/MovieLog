package com.bertholucci.core.extensions

import com.bertholucci.core.model.Genre
import com.google.gson.Gson

fun String.getGenres(): List<Genre> {
    return if (this.isEmpty()) emptyList()
    else Gson().fromJson(this, Array<Genre>::class.java).asList()
}