package com.bertholucci.data.repository

import com.bertholucci.data.MovieLogApi
import com.bertholucci.data.database.MovieDao
import com.bertholucci.data.mapper.MovieEntityMapper
import com.bertholucci.data.mapper.MovieResponseMapper
import com.bertholucci.data.model.MovieResponse
import com.bertholucci.movielog.domain.MovieRepository
import com.bertholucci.movielog.domain.model.MovieDomain
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class MovieRepositoryImpl(private val api: MovieLogApi, private val dao: MovieDao) :
    MovieRepository {

    override fun getUpcomingMovies(page: Int): Flow<List<MovieDomain>> {
        return flow {
            emit(convert(api.getUpcomingMovies(page).results))
        }
    }

    override fun getPopularMovies(page: Int): Flow<List<MovieDomain>> {
        return flow {
            emit(convert(api.getPopularMovies(page).results))
        }
    }

    override fun getNowPlayingMovies(page: Int): Flow<List<MovieDomain>> {
        return flow {
            emit(convert(api.getNowPlayingMovies(page).results))
        }
    }

    override fun getTopRatedMovies(page: Int): Flow<List<MovieDomain>> {
        return flow {
            emit(convert(api.getTopRatedMovies(page).results))
        }
    }

    override fun getMovieDetails(movieId: String): Flow<MovieDomain> {
        return flow {
            emit(MovieResponseMapper().mapToDomain(api.getMovieDetails(movieId)))
        }
    }

    override fun insertMovie(movie: MovieDomain): Flow<Unit> {
        return flow {
            emit(dao.insertMovie(MovieEntityMapper().mapFromDomain(movie)))
        }
    }

    override fun removeMovie(movie: MovieDomain): Flow<Unit> {
        return flow {
            emit(dao.removeMovie(MovieEntityMapper().mapFromDomain(movie)))
        }
    }

    override fun getMovieByIdFromDB(id: Int): Flow<MovieDomain?> {
        return flow {
            emit(MovieEntityMapper().mapToDomain(dao.getMovieByIDFromDB(id)))
        }
    }

    override fun searchMovies(query: String, page: Int): Flow<List<MovieDomain>> {
        return flow {
            emit(convert(api.searchMovies(query = query, page = page).results))
        }
    }

    override fun getFavoritesMovies(): Flow<List<MovieDomain>?> {
        return flow {
            emit(MovieEntityMapper().mapToDomainList(dao.getMovies()))
        }
    }

    private fun convert(list: List<MovieResponse>) = MovieResponseMapper().mapToDomainList(list)
}