package com.example.movies.model.data

import com.google.gson.annotations.SerializedName


data class ApiResponse (

    @SerializedName("page"          ) var page         : Int?               = null,
    @SerializedName("results"       ) var results      : List<Movie> = emptyList(),
    @SerializedName("total_pages"   ) var totalPages   : Int?               = null,
    @SerializedName("total_results" ) var totalResults : Int?               = null

)