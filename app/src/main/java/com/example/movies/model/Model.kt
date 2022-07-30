package com.example.movies.model

import com.example.movies.Contract
import com.example.movies.model.data.*
import com.example.movies.model.network.RetrofitService
import retrofit2.Callback

class Model : Contract.Model {

    companion object {
        private const val API_KEY = "032c9cedbfb4a49a4ef35763a4f395b1"
        private const val VOTE_COUNT = "1000"
    }

    private val service = RetrofitService.getService()

    override fun getMovies(sorting: Sorting, page: Int, callback: Callback<MovieResponse>) {
        service.getMovies(API_KEY, sorting.queryParam, VOTE_COUNT, "$page").enqueue(callback)
    }

    override fun getMovieDetails(movieId: Int, callback: Callback<MovieDetails>) {
        service.getMovieDetails("$movieId", API_KEY).enqueue(callback)
    }

    override fun getCast(movieId: Int, callback: Callback<CastResponse>) {
        service.getCast("$movieId", API_KEY).enqueue(callback)
    }

    override fun getVideos(movieId: Int, callback: Callback<VideoResponse>) {
        service.getVideos("$movieId", API_KEY).enqueue(callback)
    }
}