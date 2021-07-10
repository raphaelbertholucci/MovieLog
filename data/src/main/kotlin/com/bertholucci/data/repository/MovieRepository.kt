package com.bertholucci.data.repository

import com.bertholucci.data.MovieLogApi
import com.bertholucci.data.model.MovieResponse
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class MovieRepository(private val api: MovieLogApi) {

    fun getMovieDetails(movieId: String): Flow<MovieResponse> {
        return flow {
            emit(api.getMovieDetails(movieId))
        }
    }
}