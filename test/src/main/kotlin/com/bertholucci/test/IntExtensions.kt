package com.bertholucci.test

import android.view.View
import androidx.test.espresso.Espresso
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.matcher.ViewMatchers
import org.hamcrest.CoreMatchers
import org.hamcrest.Matcher


fun Int.isDisplayed(parentId: Int = 0) {
    Espresso.onView(checkThisOrParent(parentId))
        .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
}

fun Int.isNotDisplayed(parentId: Int = 0) {
    Espresso.onView(checkThisOrParent(parentId))
        .check(ViewAssertions.matches(CoreMatchers.not(ViewMatchers.isDisplayed())))
}

fun Int.isTextDisplayed() {
    Espresso.onView(ViewMatchers.withId(this))
        .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
}

fun Int.isTextNotDisplayed() {
    Espresso.onView(ViewMatchers.withId(this)).check(ViewAssertions.doesNotExist())
}

fun Int.hasText(text: String) {
    Espresso.onView(ViewMatchers.withId(this))
        .check(ViewAssertions.matches(ViewMatchers.withText(text)))
}

fun Int.hasText(textId: Int) {
    Espresso.onView(ViewMatchers.withId(this))
        .check(ViewAssertions.matches(ViewMatchers.withText(textId)))
}

fun Int.click() {
    Espresso.onView(ViewMatchers.withId(this)).perform(ViewActions.click())
}

private fun Int.checkThisOrParent(parentId: Int): Matcher<View> {
    var matcher = ViewMatchers.withId(this)
    if (parentId != 0) {
        matcher = CoreMatchers.allOf(
            ViewMatchers.withId(this),
            ViewMatchers.withParent(ViewMatchers.withId(parentId))
        )
    }
    return matcher
}