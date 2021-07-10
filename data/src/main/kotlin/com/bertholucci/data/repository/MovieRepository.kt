package com.bertholucci.data.repository

import com.bertholucci.data.MovieLogApi
import com.bertholucci.data.database.MovieDao
import com.bertholucci.data.model.MovieResponse
import com.bertholucci.data.model.entity.MovieEntity
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class MovieRepository(private val api: MovieLogApi, private val dao: MovieDao) {

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

    fun getMovieById(id: Int): Flow<MovieEntity> {
        return flow {
            emit(dao.getMovieByID(id))
        }
    }
}