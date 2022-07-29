package com.example.movies.presenter

import com.example.movies.Contract
import com.example.movies.model.data.MovieResponse
import com.example.movies.model.data.Sorting
import retrofit2.Call
import retrofit2.Response

class ListPresenter(
    private var view: Contract.ListView?,
    private val model: Contract.Model,
) : Contract.ListPresenter {

    override fun requestMovies(sorting: Sorting, page: Int) {
        model.getMovies(sorting, page, object : retrofit2.Callback<MovieResponse> {
            override fun onResponse(call: Call<MovieResponse>, response: Response<MovieResponse>) {
                response.body()?.let {
                    view?.onMovieResponse(it.results)
                }
            }

            override fun onFailure(call: Call<MovieResponse>, t: Throwable) {
                view?.onFailure(t)
            }
        })
    }

    override fun onDestroy() {
        view = null
    }
}