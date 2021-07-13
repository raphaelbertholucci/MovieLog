package com.bertholucci.data.repository

import com.bertholucci.data.MovieLogApi
import com.bertholucci.data.database.MovieDao
import com.bertholucci.data.model.MovieResponse
import com.bertholucci.data.model.MovieType
import com.bertholucci.data.model.entity.MovieEntity
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class MovieRepository(private val api: MovieLogApi, private val dao: MovieDao) {

    fun getMoviesByType(type: MovieType, page: Int = 1): Flow<List<MovieResponse>> {
        return when (type) {
            MovieType.UPCOMING -> getUpcomingMovies(page)
            MovieType.POPULAR -> getPopularMovies(page)
            MovieType.NOW_PLAYING -> getNowPlayingMovies(page)
            MovieType.TOP_RATED -> getTopRatedMovies(page)
        }
    }

    private fun getUpcomingMovies(page: Int = 1): Flow<List<MovieResponse>> {
        return flow {
            emit(api.getUpcomingMovies(page).results)
        }
    }

    private fun getPopularMovies(page: Int = 1): Flow<List<MovieResponse>> {
        return flow {
            emit(api.getPopularMovies(page).results)
        }
    }

    private fun getNowPlayingMovies(page: Int = 1): Flow<List<MovieResponse>> {
        return flow {
            emit(api.getNowPlayingMovies(page).results)
        }
    }

    private fun getTopRatedMovies(page: Int = 1): Flow<List<MovieResponse>> {
        return flow {
            emit(api.getTopRatedMovies(page).results)
        }
    }

    fun getMovieDetails(movieId: String): Flow<MovieResponse> {
        return flow {
            emit(api.getMovieDetails(movieId))
        }
    }

    suspend fun insertMovie(movie: MovieEntity) {
        dao.insertMovie(movie)
    }

    suspend fun removeMovie(movie: MovieEntity) {
        dao.removeMovie(movie)
    }

    fun getMovieById(id: Int): Flow<MovieEntity?> {
        return flow {
            emit(dao.getMovieByID(id))
        }
    }

    fun searchMovies(query: String, page: Int = 1): Flow<List<MovieResponse>> {
        return flow {
            emit(api.searchMovies(query = query, page = page).results)
        }
    }
}