package com.bertholucci.movielog.domain.interactor

import com.bertholucci.movielog.domain.MovieRepository
import com.bertholucci.movielog.domain.UseCase
import com.bertholucci.movielog.domain.model.MovieDomain
import kotlinx.coroutines.flow.Flow

class GetFavorites(private val repository: MovieRepository) : UseCase<Any, List<MovieDomain>?>() {

    override fun executeUseCase(requestValues: Any): Flow<List<MovieDomain>?> {
        return repository.getFavoritesMovies()
    }
}