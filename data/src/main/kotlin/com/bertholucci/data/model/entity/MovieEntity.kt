package com.bertholucci.data.model.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "movies")
data class MovieEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val popularity: Double? = 0.0,
    val voteCount: Int? = 0,
    val posterPath: String? = "",
    val backdropPath: String? = "",
    val genres: String? = "",
    val title: String? = "",
    val voteAverage: Double? = 0.0,
    val overview: String? = "",
    val releaseDate: String? = "",
    val runtime: String? = ""
)