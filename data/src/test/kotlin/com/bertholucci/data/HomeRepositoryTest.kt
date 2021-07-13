package com.bertholucci.data

import com.bertholucci.data.model.MovieListResponse
import com.bertholucci.data.model.MovieResponse
import io.mockk.coEvery
import io.mockk.impl.annotations.RelaxedMockK
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Assert.assertEquals
import org.junit.Test

@ExperimentalCoroutinesApi
class HomeRepositoryTest : BaseTest<HomeRepository>() {

    @RelaxedMockK
    private lateinit var api: MovieLogApi

    override fun init() {
        agent = HomeRepository(api)
    }

    @Test
    fun getUpcomingMovies() = runBlockingTest {
        coEvery { api.getUpcomingMovies(any()) } returns movieListMock()

        agent.getUpcomingMovies().collect {
            assertEquals(movieListMock().results, it)
        }
    }

    @Test
    fun getPopularMovies() = runBlockingTest {
        coEvery { api.getPopularMovies(any()) } returns movieListMock()

        agent.getPopularMovies().collect {
            assertEquals(movieListMock().results, it)
        }
    }

    @Test
    fun getNowPlayingMovies() = runBlockingTest {
        coEvery { api.getNowPlayingMovies(any()) } returns movieListMock()

        agent.getNowPlayingMovies().collect {
            assertEquals(movieListMock().results, it)
        }
    }

    private fun movieListMock() = MovieListResponse(
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
                runtime = "100"
            )
        )
    )
}