package com.bertholucci.test

import androidx.test.espresso.Espresso
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.matcher.ViewMatchers

fun String.isTextDisplayed() {
    Espresso.onView(ViewMatchers.withText(this))
        .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
}