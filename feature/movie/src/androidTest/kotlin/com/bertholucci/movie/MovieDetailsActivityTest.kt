package com.bertholucci.movie

import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.After
import org.junit.Test
import org.junit.runner.RunWith
import org.koin.core.context.stopKoin

@RunWith(AndroidJUnit4::class)
class MovieDetailsActivityTest {

    @After
    fun tearDown() {
        stopKoin()
    }

    @Test
    fun showCorrectTexts() {
        setupView {
            mockMovie()
        } launchView {
        } check {
            hasRightTexts()
        }
    }

    @Test
    fun clickOnSave_shouldShowSnackBar_withMovieSavedText() {
        setupView {
            mockMovie()
        } launchView {
            clickOnSave()
        } check {
            snackIsVisible()
        }
    }

    @Test
    fun clickOnNotSave_shouldShowSnackBar_withMovieRemovedText() {
        setupView {
            mockMovie()
            mockMovieFavorite()
        } launchView {
            clickOnSave()
        } check {
            snackMovieRemovedIsVisible()
        }
    }
}