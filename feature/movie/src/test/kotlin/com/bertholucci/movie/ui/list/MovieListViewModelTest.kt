package com.bertholucci.movie.ui.list

import com.bertholucci.core.model.Movie
import com.bertholucci.data.helpers.Response
import com.bertholucci.data.model.MovieResponse
import com.bertholucci.movielog.domain.model.MovieType
import com.bertholucci.data.repository.MovieRepositoryImpl
import com.bertholucci.movie.ui.BaseViewModelTest
import io.mockk.coEvery
import io.mockk.impl.annotations.RelaxedMockK
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Assert.assertEquals
import org.junit.Test

@ExperimentalCoroutinesApi
class MovieListViewModelTest : BaseViewModelTest<MovieListViewModel>() {

    @RelaxedMockK
    private lateinit var repositoryImpl: MovieRepositoryImpl

    override fun init() {
        viewModel = MovieListViewModel(repositoryImpl)
    }

    @Test
    fun getMovies(): Unit = runBlockingTest {
        coEvery { repositoryImpl.getMoviesByType(any(), any()) } returns flow {
            emit(movieListMock())
        }

        viewModel.getMovies(com.bertholucci.movielog.domain.model.MovieType.TOP_RATED)

        viewModel.movieList.observeForever {
            assertEquals(Response.Success(movieListMock().map(::Movie).toMutableList()), it)
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