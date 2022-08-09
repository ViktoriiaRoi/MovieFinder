package com.example.movies.model.data

import com.google.gson.annotations.SerializedName

data class Video(
    @SerializedName("name") var name: String? = null,
    @SerializedName("key") var key: String? = null
)