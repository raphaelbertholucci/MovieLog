package com.bertholucci.data.mapper

import com.bertholucci.core.base.BaseMapper
import com.bertholucci.data.model.entity.MovieEntity
import com.bertholucci.movielog.domain.model.MovieDomain

class MovieEntityMapper : BaseMapper<MovieEntity?, MovieDomain> {

    override fun mapFromDomain(domain: MovieDomain): MovieEntity {
        return MovieEntity(
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
            genres = GenreResponseMapper().mapGenresFromDomainToEntity(domain.genres)
        )
    }

    override fun mapToDomain(model: MovieEntity?): MovieDomain {
        return MovieDomain(
            popularity = model?.popularity,
            voteAverage = model?.voteAverage,
            voteCount = model?.voteCount,
            posterPath = model?.posterPath,
            id = model?.id,
            backdropPath = model?.backdropPath,
            title = model?.title,
            overview = model?.overview,
            releaseDate = model?.releaseDate,
            runtime = model?.runtime,
            originalLanguage = model?.originalLanguage,
            genres = GenreResponseMapper().mapGenresFromEntityToDomain(model?.genres)
        )
    }

    fun mapToDomainList(list: List<MovieEntity>): List<MovieDomain> = list.map {
        MovieEntityMapper().mapToDomain(it)
    }
}