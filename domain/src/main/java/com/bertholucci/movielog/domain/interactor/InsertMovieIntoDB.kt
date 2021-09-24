package com.bertholucci.movielog.domain.interactor

import com.bertholucci.movielog.domain.MovieRepository
import com.bertholucci.movielog.domain.UseCase
import com.bertholucci.movielog.domain.model.MovieDomain
import kotlinx.coroutines.flow.Flow

class InsertMovieIntoDB(private val repository: MovieRepository) : UseCase<MovieDomain, Unit>() {
    override fun executeUseCase(requestValues: MovieDomain): Flow<Unit> {
        return repository.insertMovie(requestValues)
    }
}