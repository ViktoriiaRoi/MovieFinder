package com.example.movies.model

import com.example.movies.Contract
import com.example.movies.model.data.Sorting
import com.example.movies.model.network.RetrofitService

class Model : Contract.Model {

    companion object {
        private const val API_KEY = "032c9cedbfb4a49a4ef35763a4f395b1"
    }

    private val service = RetrofitService.getService()

    override fun getMovies(sorting: Sorting, page: Int) =
        service.getMovies(API_KEY, sorting.queryParam, sorting.voteCount, "$page")

    override fun getMovieDetails(movieId: Int) =
        service.getMovieDetails("$movieId", API_KEY)

    override fun getCast(movieId: Int) =
        service.getCast("$movieId", API_KEY)

    override fun getVideos(movieId: Int) =
        service.getVideos("$movieId", API_KEY)
}