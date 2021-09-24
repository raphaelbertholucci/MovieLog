package com.bertholucci.movielog.domain.interactor

import com.bertholucci.movielog.domain.MovieRepository
import com.bertholucci.movielog.domain.UseCase
import com.bertholucci.movielog.domain.model.MovieDomain
import kotlinx.coroutines.flow.Flow

class GetUpcomingMovies(private val repository: MovieRepository) : UseCase<Int, List<MovieDomain>?>() {

    override fun executeUseCase(requestValues: Int): Flow<List<MovieDomain>?> {
        return repository.getUpcomingMovies(page = requestValues)
    }
}