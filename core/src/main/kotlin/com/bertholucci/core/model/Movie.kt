package com.bertholucci.core.model

data class Movie(
    val popularity: Double? = 0.0,
    val voteCount: Int? = 0,
    val posterPath: String? = "",
    val id: Int = 0,
    val backdropPath: String? = "",
    val title: String? = "",
    val voteAverage: Double? = 0.0,
    val overview: String? = "",
    val releaseDate: String? = "",
    val runtime: String? = "",
    val originalLanguage: String? = "",
    val genres: List<Genre> = listOf(),
    var isFavorite: Boolean = false
)