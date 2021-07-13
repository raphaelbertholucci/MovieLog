package com.bertholucci.movie

import com.bertholucci.data.model.MovieResponse
import com.bertholucci.data.model.MovieType
import com.bertholucci.data.repository.MovieRepository
import com.bertholucci.movie.ui.list.MovieListViewModel
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.impl.annotations.RelaxedMockK
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

@ExperimentalCoroutinesApi
class MovieListViewModelTest {

    @RelaxedMockK
    private lateinit var repository: MovieRepository

    private lateinit var viewModel: MovieListViewModel

    @Before
    fun setup() {
        MockKAnnotations.init(this)
        viewModel = MovieListViewModel(repository)
    }

    @Test
    fun getMovies(): Unit = runBlockingTest {
        coEvery { repository.getMoviesByType(any(), any()) } returns flow {
            emit(movieListMock())
        }

        viewModel.getMovies(MovieType.TOP_RATED)

        viewModel.movieList.observeForever {
            assertEquals(movieListMock().toMutableList(), it)
        }
    }

    private fun movieListMock() =
        listOf(
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
        )
}