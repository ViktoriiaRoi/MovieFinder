package com.example.movies.model.network

import com.example.movies.model.data.ApiResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface MovieApi {

    @GET("discover/movie")
    fun getMovies(@Query("api_key") apiKey: String): Call<ApiResponse>
}