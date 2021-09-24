package com.bertholucci.movielog.domain.model

class MovieDomain(
    val popularity: Double?,
    val voteCount: Int?,
    val posterPath: String?,
    val id: Int?,
    val backdropPath: String?,
    val title: String?,
    val voteAverage: Double?,
    val overview: String?,
    val releaseDate: String?,
    val runtime: String?,
    val originalLanguage: String?,
    val genres: List<GenreDomain>?
)