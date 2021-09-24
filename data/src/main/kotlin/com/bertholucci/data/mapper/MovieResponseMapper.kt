package com.bertholucci.data.mapper

import com.bertholucci.core.base.BaseMapper
import com.bertholucci.data.model.MovieResponse
import com.bertholucci.movielog.domain.model.MovieDomain

class MovieResponseMapper : BaseMapper<MovieResponse, MovieDomain> {

    override fun mapFromDomain(domain: MovieDomain): MovieResponse {
        throw UnsupportedOperationException("Unsupported Operation")
    }

    override fun mapToDomain(model: MovieResponse): MovieDomain {
        return MovieDomain(
            popularity = model.popularity,
            voteAverage = model.voteAverage,
            voteCount = model.voteCount,
            posterPath = model.posterPath,
            id = model.id,
            backdropPath = model.backdropPath,
            title = model.title,
            overview = model.overview,
            releaseDate = model.releaseDate,
            runtime = model.runtime,
            originalLanguage = model.originalLanguage,
            genres = model.genres?.map { GenreResponseMapper().mapToDomain(it) }
        )
    }

    fun mapToDomainList(list: List<MovieResponse>): List<MovieDomain> = list.map {
        MovieResponseMapper().mapToDomain(it)
    }
}