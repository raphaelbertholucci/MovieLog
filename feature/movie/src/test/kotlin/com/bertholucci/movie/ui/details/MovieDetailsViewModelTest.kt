package com.bertholucci.movie.ui.details

import com.bertholucci.data.helpers.Response
import com.bertholucci.data.model.MovieResponse
import com.bertholucci.data.model.entity.MovieEntity
import com.bertholucci.data.repository.MovieRepository
import com.bertholucci.movie.BaseViewModelTest
import com.bertholucci.movie.model.Movie
import io.mockk.coEvery
import io.mockk.impl.annotations.RelaxedMockK
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Assert.assertEquals
import org.junit.Test

@ExperimentalCoroutinesApi
class MovieDetailsViewModelTest : BaseViewModelTest<MovieDetailsViewModel>() {

    @RelaxedMockK
    private lateinit var repository: MovieRepository

    override fun init() {
        viewModel = MovieDetailsViewModel(repository)
    }

    @Test
    fun getMovieDetail(): Unit = runBlockingTest {
        coEvery { repository.getMovieDetails(any()) } returns flow {
            emit(movieMock())
        }

        viewModel.getMovieDetail("123")

        viewModel.movie.observeForever {
            assertEquals(Response.Success(Movie(movieMock())), it)
        }
    }

    @Test
    fun getMovieByIDFromDB(): Unit = runBlockingTest {
        coEvery { repository.getMovieByIdFromDB(any()) } returns flow {
            emit(movieMockEntity())
        }

        viewModel.getMovieByIDFromDB(123)

        viewModel.movie.observeForever {
            assertEquals(Response.Success(Movie(movieMock())), it)
        }
    }

    private fun movieMock() =
        MovieResponse(
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
            genres = listOf(),
            originalLanguage = ""
        )

    private fun movieMockEntity() =
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
}