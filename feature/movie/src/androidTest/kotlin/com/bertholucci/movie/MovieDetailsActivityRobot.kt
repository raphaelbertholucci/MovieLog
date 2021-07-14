package com.bertholucci.movie

import androidx.test.core.app.ActivityScenario
import com.bertholucci.data.model.MovieResponse
import com.bertholucci.data.repository.MovieRepository
import com.bertholucci.movie.extensions.click
import com.bertholucci.movie.extensions.hasText
import com.bertholucci.movie.ui.details.MovieDetailsActivity
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.flow.flow


fun setupView(func: MovieDetailsActivityRobot.() -> Unit) =
    MovieDetailsActivityRobot().apply(func)

class MovieDetailsActivityRobot {

    private val repository: MovieRepository = mockk(relaxed = true)

    fun mockMovie() {
        coEvery {
            repository.getMovieDetails(any())
        } returns flow {
            emit(mock())
        }
    }

    infix fun launchView(func: MovieDetailsActivityAction.() -> Unit): MovieDetailsActivityAction {
        ActivityScenario.launch(MovieDetailsActivity::class.java)
        return MovieDetailsActivityAction().apply(func)
    }
}

class MovieDetailsActivityAction {
    infix fun check(func: MovieDetailsActivityResult.() -> Unit) =
        MovieDetailsActivityResult().apply(func)

    fun clickOnSave() {
        R.id.iv_save.click()
    }
}

class MovieDetailsActivityResult {

    fun hasRightTexts() {
        R.id.tv_title.hasText("Avengers")
        R.id.tv_rate.hasText("9.0")
        R.id.tv_runtime.hasText("100 min")
        R.id.tv_language.hasText("EN")
        R.id.tv_about.hasText("Lorem Ipsum")
    }
}

fun mock() = MovieResponse(
    popularity = 10.0,
    voteCount = 123,
    posterPath = "",
    id = 0,
    backdropPath = "",
    title = "Avengers",
    voteAverage = 9.0,
    overview = "Lorem Ipsum",
    releaseDate = "2020-02-21",
    runtime = "100",
    genres = listOf(),
    originalLanguage = "en"
)