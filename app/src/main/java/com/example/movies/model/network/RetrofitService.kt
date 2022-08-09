package com.example.movies.model.network

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


object RetrofitService {
    private const val BASE_URL = "https://api.themoviedb.org/3/"

    private var retrofit: Retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    fun getService(): MovieApi = retrofit.create(MovieApi::class.java)
}