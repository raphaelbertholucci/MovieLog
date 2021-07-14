package com.bertholucci.data

import com.bertholucci.data.database.MovieDao
import com.bertholucci.data.model.MovieListResponse
import com.bertholucci.data.model.MovieResponse
import com.bertholucci.data.model.MovieType
import com.bertholucci.data.model.entity.MovieEntity
import com.bertholucci.data.repository.MovieRepository
import io.mockk.coEvery
import io.mockk.impl.annotations.RelaxedMockK
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Assert.assertEquals
import org.junit.Test

@ExperimentalCoroutinesApi
class MovieRepositoryTest : BaseTest<MovieRepository>() {

    @RelaxedMockK
    private lateinit var api: MovieLogApi

    @RelaxedMockK
    private lateinit var dao: MovieDao

    override fun init() {
        agent = MovieRepository(api, dao)
    }

    @Test
    fun getUpcomingMovies() = runBlockingTest {
        coEvery { api.getUpcomingMovies(any()) } returns movieListMock()

        agent.getMoviesByType(MovieType.UPCOMING).collect {
            assertEquals(movieListMock().results, it)
        }
    }

    @Test
    fun getPopularMovies() = runBlockingTest {
        coEvery { api.getPopularMovies(any()) } returns movieListMock()

        agent.getMoviesByType(MovieType.POPULAR).collect {
            assertEquals(movieListMock().results, it)
        }
    }

    @Test
    fun getNowPlayingMovies() = runBlockingTest {
        coEvery { api.getNowPlayingMovies(any()) } returns movieListMock()

        agent.getMoviesByType(MovieType.NOW_PLAYING).collect {
            assertEquals(movieListMock().results, it)
        }
    }

    @Test
    fun getTopRatedMovies() = runBlockingTest {
        coEvery { api.getTopRatedMovies(any()) } returns movieListMock()

        agent.getMoviesByType(MovieType.TOP_RATED).collect {
            assertEquals(movieListMock().results, it)
        }
    }

    @Test
    fun getMovieDetails() = runBlockingTest {
        coEvery { api.getMovieDetails(any()) } returns movieMock()

        agent.getMovieDetails("123").collect {
            assertEquals(movieMock(), it)
        }
    }

    @Test
    fun getMoviesFromDB() = runBlockingTest {
        coEvery { dao.getMovies() } returns flow {
            emit(movieEntityListMock())
        }

        agent.getFavoritesMovies().collect {
            assertEquals(movieEntityListMock(), it)
        }
    }

    @Test
    fun getMovieByIDFromDB() = runBlockingTest {
        coEvery { dao.getMovieByIDFromDB(any()) } returns movieEntityMock()
        assertEquals(movieEntityMock(), dao.getMovieByIDFromDB(123))
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
                runtime = "100",
                genres = listOf(),
                originalLanguage = ""
            )
        )
    )

    private fun movieEntityListMock() = listOf(
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
    )

    private fun movieEntityMock() =
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
}