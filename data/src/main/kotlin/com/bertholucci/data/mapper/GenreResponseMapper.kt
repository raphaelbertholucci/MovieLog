package com.bertholucci.data.mapper

import com.bertholucci.core.base.BaseMapper
import com.bertholucci.data.model.GenreResponse
import com.bertholucci.movielog.domain.model.GenreDomain
import com.google.gson.Gson

class GenreResponseMapper : BaseMapper<GenreResponse, GenreDomain> {
    override fun mapFromDomain(domain: GenreDomain): GenreResponse {
        throw UnsupportedOperationException("Unsupported Operation")
    }

    override fun mapToDomain(model: GenreResponse): GenreDomain {
        return GenreDomain(
            id = model.id,
            name = model.name
        )
    }

    fun mapGenresFromEntityToDomain(genres: String?): List<GenreDomain> {
        return genres?.run {
            if (isEmpty()) emptyList()
            else Gson().fromJson(this, Array<GenreDomain>::class.java).asList()
        } ?: listOf()
    }

    fun mapGenresFromDomainToEntity(list: List<GenreDomain>?): String {
        return list?.run { Gson().toJson(this) } ?: ""
    }
}