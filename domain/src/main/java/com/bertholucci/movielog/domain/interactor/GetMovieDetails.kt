package com.bertholucci.movielog.domain.interactor

import com.bertholucci.movielog.domain.MovieRepository
import com.bertholucci.movielog.domain.UseCase
import com.bertholucci.movielog.domain.model.MovieDomain
import kotlinx.coroutines.flow.Flow

class GetMovieDetails(private val repository: MovieRepository) : UseCase<String, MovieDomain>() {

    override fun executeUseCase(requestValues: String): Flow<MovieDomain> {
        return repository.getMovieDetails(requestValues)
    }
}