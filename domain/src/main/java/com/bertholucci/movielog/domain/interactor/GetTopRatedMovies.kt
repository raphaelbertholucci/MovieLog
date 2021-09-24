package com.bertholucci.movielog.domain.interactor

import com.bertholucci.movielog.domain.MovieRepository
import com.bertholucci.movielog.domain.UseCase
import com.bertholucci.movielog.domain.model.MovieDomain
import kotlinx.coroutines.flow.Flow

class GetTopRatedMovies(private val repository: MovieRepository) :
    UseCase<Int, List<MovieDomain>?>() {

    override fun executeUseCase(requestValues: Int): Flow<List<MovieDomain>?> {
        return repository.getTopRatedMovies(page = requestValues)
    }
}