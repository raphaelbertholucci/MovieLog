package com.bertholucci.movie

import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class MovieDetailsActivityTest {

    @Test
    fun showCorrectTexts() {
        setupView {
            mockMovie()
        } launchView {
        } check {
            hasRightTexts()
        }
    }
}