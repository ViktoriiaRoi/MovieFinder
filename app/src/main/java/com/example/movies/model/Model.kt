package com.example.movies.model

import com.example.movies.Contract
import com.example.movies.model.data.ApiResponse
import com.example.movies.model.network.RetrofitService

class Model : Contract.Model {

    companion object {
        private const val API_KEY = "032c9cedbfb4a49a4ef35763a4f395b1"
    }

    override fun getMovies(callback: retrofit2.Callback<ApiResponse>) {
        val service = RetrofitService.getService()
        service.getMovies(API_KEY).enqueue(callback)
    }
}