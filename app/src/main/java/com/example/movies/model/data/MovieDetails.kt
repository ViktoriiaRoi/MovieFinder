package com.example.movies.model.data

import com.google.gson.annotations.SerializedName

data class MovieDetails(
    @SerializedName("genres") var genres: List<Genre> = emptyList(),
    @SerializedName("runtime") var runtime: Int? = null,
)
