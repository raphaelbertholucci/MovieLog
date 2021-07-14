package com.bertholucci.movie

import android.content.Intent
import androidx.test.core.app.ActivityScenario
import androidx.test.platform.app.InstrumentationRegistry
import com.bertholucci.core.component.ErrorDialog
import com.bertholucci.core.component.LoadingDialog
import com.bertholucci.core.route.intentToMovie
import com.bertholucci.data.model.MovieResponse
import com.bertholucci.data.model.entity.MovieEntity
import com.bertholucci.data.repository.MovieRepository
import com.bertholucci.movie.extensions.click
import com.bertholucci.movie.extensions.hasText
import com.bertholucci.movie.extensions.isTextDisplayed
import com.bertholucci.movie.ui.details.MovieDetailsActivity
import com.bertholucci.movie.ui.details.MovieDetailsViewModel
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.flow.flow
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.core.context.startKoin
import org.koin.dsl.module


fun setupView(func: MovieDetailsActivityRobot.() -> Unit) =
    MovieDetailsActivityRobot().apply(func)

class MovieDetailsActivityRobot {

    private val repository: MovieRepository = mockk(relaxed = true)

    init {
        startKoin {
            modules(
                listOf(
                    module { viewModel { MovieDetailsViewModel(repository = repository) } },
                    module { single { LoadingDialog() } },
                    module { single { ErrorDialog() } }
                )
            )
        }
    }

    fun mockMovie() {
        coEvery {
            repository.getMovieDetails(any())
        } returns flow {
            emit(mock())
        }
    }

    fun mockMovieFavorite() {
        coEvery {
            repository.getMovieByIdFromDB(any())
        } returns flow {
            emit(mockEntity())
        }
    }

    infix fun launchView(func: MovieDetailsActivityAction.() -> Unit): MovieDetailsActivityAction {
        val context = InstrumentationRegistry.getInstrumentation().targetContext
        val intent = Intent(context.intentToMovie("123"))
        ActivityScenario.launch<MovieDetailsActivity>(intent)
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

    fun snackIsVisible() {
        "Movie saved to favorites with success!".isTextDisplayed()
    }

    fun snackMovieRemovedIsVisible() {
        "Movie removed from favorites!".isTextDisplayed()
    }
}

private fun mock() = MovieResponse(
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

private fun mockEntity() =
    MovieEntity(
        popularity = 10.0,
        voteCount = 123,
        posterPath = "",
        id = 0,
        backdropPath = "",
        title = "",
        voteAverage = 9.0,
        overview = "",
        releaseDate = "2020-02-21",
        runtime = "100",
        genres = "",
        originalLanguage = ""
    )