package com.bertholucci.movielog.domain.interactor

import com.bertholucci.movielog.domain.MovieRepository
import com.bertholucci.movielog.domain.UseCase
import com.bertholucci.movielog.domain.model.MovieDomain
import kotlinx.coroutines.flow.Flow

class SearchMovies(private val repository: MovieRepository) :
    UseCase<Pair<String, Int>, List<MovieDomain>?>() {

    override fun executeUseCase(requestValues: Pair<String, Int>): Flow<List<MovieDomain>?> {
        return repository.searchMovies(query = requestValues.first, page = requestValues.second)
    }
}