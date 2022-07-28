package com.example.movies.model.network

import com.example.movies.model.data.ApiResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface MovieApi {

    companion object {
        private const val API_PARAM = "api_key"
        private const val SORT_PARAM = "sort_by"
        private const val VOTE_PARAM = "vote_count.gte"
        private const val PAGE_PARAM = "page"
    }

    @GET("discover/movie")
    fun getMovies(
        @Query(API_PARAM) apiKey: String,
        @Query(SORT_PARAM) sorting: String,
        @Query(VOTE_PARAM) vote_count: String,
        @Query(PAGE_PARAM) page: String
    ): Call<ApiResponse>
}