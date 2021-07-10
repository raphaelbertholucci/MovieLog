package com.bertholucci.data.repository

import com.bertholucci.data.MovieLogApi
import com.bertholucci.data.model.MovieResponse
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class HomeRepository(private val api: MovieLogApi) {

    fun getUpcomingMovies(page: Int = 1): Flow<List<MovieResponse>> {
        return flow {
            emit(api.getUpcomingMovies(page).results)
        }
    }

    fun getPopularMovies(page: Int = 1): Flow<List<MovieResponse>> {
        return flow {
            emit(api.getPopularMovies(page).results)
        }
    }

    fun getNowPlayingMovies(page: Int = 1): Flow<List<MovieResponse>> {
        return flow {
            emit(api.getNowPlayingMovies(page).results)
        }
    }
}