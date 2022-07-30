package com.example.movies.model.network

import com.example.movies.model.data.MovieDetails
import com.example.movies.model.data.MovieResponse
import com.example.movies.model.data.VideoResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
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
    ): Call<MovieResponse>

    @GET("movie/{id}")
    fun getMovieDetails(
        @Path("id") movieId: String,
        @Query(API_PARAM) apiKey: String
    ): Call<MovieDetails>

    @GET("movie/{id}/videos")
    fun getVideos(
        @Path("id") movieId: String,
        @Query(API_PARAM) apiKey: String
    ): Call<VideoResponse>
}