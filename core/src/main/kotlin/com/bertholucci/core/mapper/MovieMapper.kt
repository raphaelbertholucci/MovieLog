package com.bertholucci.core.mapper

import com.bertholucci.core.base.BaseMapper
import com.bertholucci.core.model.Movie
import com.bertholucci.movielog.domain.model.MovieDomain

class MovieMapper : BaseMapper<Movie, MovieDomain> {

    override fun mapFromDomain(domain: MovieDomain): Movie {
        return Movie(
            popularity = domain.popularity,
            voteAverage = domain.voteAverage,
            voteCount = domain.voteCount,
            posterPath = domain.posterPath,
            id = domain.id ?: 0,
            backdropPath = domain.backdropPath,
            title = domain.title,
            overview = domain.overview,
            releaseDate = domain.releaseDate,
            runtime = domain.runtime,
            originalLanguage = domain.originalLanguage,
            genres = GenreMapper().mapFromDomainList(domain.genres ?: emptyList())
        )
    }

    override fun mapToDomain(model: Movie): MovieDomain {
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
            genres = GenreMapper().mapToDomainList(model.genres)
        )
    }

    fun mapFromDomainList(list: List<MovieDomain>?) = list?.map {
        mapFromDomain(it)
    } ?: emptyList()
}