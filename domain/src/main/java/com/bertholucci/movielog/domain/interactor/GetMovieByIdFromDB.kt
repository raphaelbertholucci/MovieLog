package com.bertholucci.movielog.domain.interactor

import com.bertholucci.movielog.domain.MovieRepository
import com.bertholucci.movielog.domain.UseCase
import com.bertholucci.movielog.domain.model.MovieDomain
import kotlinx.coroutines.flow.Flow

class GetMovieByIdFromDB(private val repository: MovieRepository) : UseCase<Int, MovieDomain?>() {

    override fun executeUseCase(requestValues: Int): Flow<MovieDomain?> {
        return repository.getMovieByIdFromDB(requestValues)
    }
}