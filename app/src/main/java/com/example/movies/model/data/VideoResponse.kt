package com.example.movies.model.data

import com.google.gson.annotations.SerializedName

data class VideoResponse(
    @SerializedName("id") var id: Int? = null,
    @SerializedName("results") var results: List<Video> = emptyList(),
)