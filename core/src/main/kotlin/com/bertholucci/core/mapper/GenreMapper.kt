package com.bertholucci.core.mapper

import com.bertholucci.core.base.BaseMapper
import com.bertholucci.core.model.Genre
import com.bertholucci.movielog.domain.model.GenreDomain

class GenreMapper : BaseMapper<Genre, GenreDomain> {

    override fun mapFromDomain(domain: GenreDomain): Genre {
        return Genre(
            id = domain.id ?: 0,
            name = domain.name ?: ""
        )
    }

    override fun mapToDomain(model: Genre): GenreDomain {
        return GenreDomain(
            id = model.id,
            name = model.name
        )
    }

    fun mapFromDomainList(list: List<GenreDomain>) = list.map {
        mapFromDomain(it)
    }

    fun mapToDomainList(list: List<Genre>) = list.map {
        mapToDomain(it)
    }
}