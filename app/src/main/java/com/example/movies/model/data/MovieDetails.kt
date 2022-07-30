package com.example.movies.model.data

import com.google.gson.annotations.SerializedName

data class MovieDetails(
    @SerializedName("genres") var genres: List<Genre> = emptyList(),
    @SerializedName("poster_path") var posterPath: String? = null,
    @SerializedName("production_countries") var countries: List<Country> = emptyList(),
    @SerializedName("runtime") var runtime: Int? = null,
    @SerializedName("tagline") var tagline: String? = null,
)
