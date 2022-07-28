package com.example.movies

import com.example.movies.model.data.ApiResponse
import com.example.movies.model.data.Movie

interface Contract {
    interface Model {
        fun getMovies(callback: retrofit2.Callback<ApiResponse>)
    }

    interface View {
        fun onResponse(movies: List<Movie>)
        fun onFailure(t: Throwable)
    }

    interface Presenter {
        fun requestData()
        fun onDestroy()
    }
}