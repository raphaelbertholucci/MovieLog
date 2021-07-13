package com.bertholucci.movie.model

import com.bertholucci.data.model.GenreResponse

class Genre(val id: Int, val name: String) {
    constructor(response: GenreResponse) : this(
        id = response.id ?: 0,
        name = response.name ?: ""
    )
}