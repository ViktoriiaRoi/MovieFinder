package com.example.movies.model.data

import com.google.gson.annotations.SerializedName

data class Country (
    @SerializedName("iso_3166_1" ) var iso31661 : String? = null,
    @SerializedName("name"       ) var name     : String? = null
)