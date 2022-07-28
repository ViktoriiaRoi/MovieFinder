package com.example.movies.presenter

import com.example.movies.Contract
import com.example.movies.model.data.ApiResponse
import com.example.movies.model.data.Sorting
import retrofit2.Call
import retrofit2.Response

class Presenter(
    private var view: Contract.View?,
    private val model: Contract.Model,
) : Contract.Presenter {

    override fun requestData(sorting: Sorting, page: Int) {
        model.getMovies(sorting, page, object : retrofit2.Callback<ApiResponse> {
            override fun onResponse(call: Call<ApiResponse>, response: Response<ApiResponse>) {
                response.body()?.let {
                    view?.onResponse(it.results)
                }
            }

            override fun onFailure(call: Call<ApiResponse>, t: Throwable) {
                view?.onFailure(t)
            }
        })
    }

    override fun onDestroy() {
        view = null
    }
}