package com.bertholucci.core.helpers

import com.bertholucci.data.model.MovieResponse

object CommonMocks {
    fun movieListMock() =
        listOf(
            MovieResponse(
                popularity = 10.0,
                voteCount = 123,
                posterPath = "",
                id = 0,
                backdropPath = "",
                title = "",
                voteAverage = 9.0,
                overview = "",
                releaseDate = "2020-02-21",
                runtime = "100"
            )
        )
}