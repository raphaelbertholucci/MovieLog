package com.bertholucci.movie.extensions

import androidx.test.espresso.Espresso
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.matcher.ViewMatchers
import com.bertholucci.movie.model.Genre
import com.google.gson.Gson

fun String.toRuntime(): String {
    return "$this min"
}

fun String.isTextDisplayed() {
    Espresso.onView(ViewMatchers.withText(this))
        .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
}

fun String.getGenres(): List<Genre> {
    return if (this.isEmpty()) emptyList()
    else Gson().fromJson(this, Array<Genre>::class.java).asList()
}