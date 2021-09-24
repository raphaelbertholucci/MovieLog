package com.bertholucci.home

import com.bertholucci.data.helpers.Response
import com.bertholucci.data.model.MovieResponse
import com.bertholucci.data.repository.MovieRepositoryImpl
import com.bertholucci.home.ui.home.HomeViewModel
import io.mockk.coEvery
import io.mockk.impl.annotations.RelaxedMockK
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Assert.assertEquals
import org.junit.Test

@ExperimentalCoroutinesApi
class HomeViewModelTest : BaseViewModelTest<HomeViewModel>() {

    @RelaxedMockK
    private lateinit var repositoryImpl: MovieRepositoryImpl

    override fun init() {
        viewModel = HomeViewModel(repositoryImpl)
    }

    @Test
    fun getUpcomingMovies(): Unit = runBlockingTest {
        coEvery { repositoryImpl.getUpcomingMovies() } returns flow {
            emit(movieListMock())
        }

        viewModel.getUpcomingMovies()

        viewModel.upcoming.observeForever {
            assertEquals(Response.Success(movieListMock().map(::Movie)), it)
        }
    }

    @Test
    fun getPopularMovies(): Unit = runBlockingTest {
        coEvery { repositoryImpl.getPopularMovies() } returns flow {
            emit(movieListMock())
        }

        viewModel.getPopularMovies()

        viewModel.popular.observeForever {
            assertEquals(Response.Success(movieListMock().map(::Movie)), it)
        }
    }

    @Test
    fun getNowPlayingMovies(): Unit = runBlockingTest {
        coEvery { repositoryImpl.getNowPlayingMovies() } returns flow {
            emit(movieListMock())
        }

        viewModel.getNowPlayingMovies()

        viewModel.nowPlaying.observeForever {
            assertEquals(Response.Success(movieListMock().map(::Movie)), it)
        }
    }

    @Test
    fun getTopRatedMovies(): Unit = runBlockingTest {
        coEvery { repositoryImpl.getTopRatedMovies() } returns flow {
            emit(movieListMock())
        }

        viewModel.getTopRatedMovies()

        viewModel.topRated.observeForever {
            assertEquals(Response.Success(movieListMock().map(::Movie)), it)
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