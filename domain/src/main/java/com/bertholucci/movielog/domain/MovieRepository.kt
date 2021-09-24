package com.bertholucci.movielog.domain

import com.bertholucci.movielog.domain.model.MovieDomain
import com.bertholucci.movielog.domain.model.MovieType
import kotlinx.coroutines.flow.Flow

interface MovieRepository {
    fun getUpcomingMovies(page: Int = 1): Flow<List<MovieDomain>>
    fun getPopularMovies(page: Int = 1): Flow<List<MovieDomain>>
    fun getNowPlayingMovies(page: Int = 1): Flow<List<MovieDomain>>
    fun getTopRatedMovies(page: Int = 1): Flow<List<MovieDomain>>
    fun getMovieDetails(movieId: String): Flow<MovieDomain>
    fun searchMovies(query: String, page: Int = 1): Flow<List<MovieDomain>>

    fun insertMovie(movie: MovieDomain): Flow<Unit>
    fun removeMovie(movie: MovieDomain): Flow<Unit>
    fun getMovieByIdFromDB(id: Int): Flow<MovieDomain?>
    fun getFavoritesMovies(): Flow<List<MovieDomain>?>
}