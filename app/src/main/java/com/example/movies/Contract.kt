package com.example.movies

import com.example.movies.model.data.ApiResponse
import com.example.movies.model.data.Movie
import com.example.movies.model.data.Sorting

interface Contract {
    interface Model {
        fun getMovies(sorting: Sorting, page: Int, callback: retrofit2.Callback<ApiResponse>)
    }

    interface View {
        fun onResponse(movies: List<Movie>)
        fun onFailure(t: Throwable)
    }

    interface Presenter {
        fun requestData(sorting: Sorting, page: Int)
        fun onDestroy()
    }
}