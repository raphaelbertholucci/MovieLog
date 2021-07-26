package com.bertholucci.movie.extensions

import androidx.test.espresso.Espresso
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.matcher.ViewMatchers
import com.bertholucci.core.model.Genre
import com.google.gson.Gson

fun String.toRuntime(): String {
    return "$this min"
}

fun String.isTextDisplayed() {
    Espresso.onView(ViewMatchers.withText(this))
        .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
}