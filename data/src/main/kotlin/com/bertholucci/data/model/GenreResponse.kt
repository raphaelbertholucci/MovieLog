package com.bertholucci.data.model

import com.google.gson.annotations.SerializedName

class GenreResponse(
    @SerializedName("id") val id: Int?,
    @SerializedName("name") val name: String?
)