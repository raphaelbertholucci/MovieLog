package com.bertholucci.data.repository

import com.bertholucci.data.MovieLogApi
import com.bertholucci.data.model.MovieResponse
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class TopRatedRepository(private val api: MovieLogApi) {

    fun getTopRatedMovies(page: Int = 1): Flow<List<MovieResponse>> {
        return flow {
            emit(api.getTopRatedMovies(page).results)
        }
    }
}