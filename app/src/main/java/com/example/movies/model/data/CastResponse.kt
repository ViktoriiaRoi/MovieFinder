package com.example.movies.model.data

import com.google.gson.annotations.SerializedName

data class CastResponse(
    @SerializedName("cast") var cast: List<Actor> = emptyList(),
)