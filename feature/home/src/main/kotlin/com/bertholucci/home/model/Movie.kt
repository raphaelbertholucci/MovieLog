package com.bertholucci.home.model

import com.bertholucci.data.model.MovieResponse

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
    var isFavorite: Boolean = false
) {
    constructor(response: MovieResponse) : this(
        id = response.id,
        title = response.title,
        popularity = response.popularity,
        voteCount = response.voteCount,
        posterPath = response.posterPath,
        backdropPath = response.backdropPath,
        voteAverage = response.voteAverage,
        overview = response.overview,
        releaseDate = response.releaseDate,
        runtime = response.runtime
    )
}