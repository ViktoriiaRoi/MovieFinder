package com.example.movies.model.data

import com.google.gson.annotations.SerializedName

data class Actor(
    @SerializedName("name") var name: String? = null,
    @SerializedName("profile_path") var profilePath: String? = null,
    @SerializedName("character") var character: String? = null,
)