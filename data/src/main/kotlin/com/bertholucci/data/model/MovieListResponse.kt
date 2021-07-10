package com.bertholucci.data.model

import com.google.gson.annotations.SerializedName

class MovieListResponse(@SerializedName("results") val results: List<MovieResponse>)