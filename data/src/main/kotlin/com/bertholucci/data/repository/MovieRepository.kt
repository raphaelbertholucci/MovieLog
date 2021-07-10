package com.bertholucci.data.repository

import com.bertholucci.data.MovieLogApi
import com.bertholucci.data.database.MovieDao
import com.bertholucci.data.model.MovieResponse
import com.bertholucci.data.model.entity.MovieEntity
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch

class MovieRepository(private val api: MovieLogApi, private val dao: MovieDao) {

    fun getMovieDetails(movieId: String): Flow<MovieResponse> {
        return flow {
            emit(api.getMovieDetails(movieId))
        }
    }

    fun insertMovie(movie: MovieEntity) {
        GlobalScope.launch {
            dao.insertMovie(movie)
        }
    }

    fun removeMovie(movie: MovieEntity) {
        GlobalScope.launch {
            dao.removeMovie(movie)
        }
    }

    fun getMovieById(id: Int): Flow<MovieEntity> {
        return flow {
            emit(dao.getMovieByID(id))
        }
    }
}