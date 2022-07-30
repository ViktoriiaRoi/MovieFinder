package com.example.movies.presenter

import com.example.movies.Contract
import com.example.movies.model.data.MovieDetails
import com.example.movies.model.data.VideoResponse
import retrofit2.Call
import retrofit2.Response

class DetailPresenter(
    private var view: Contract.DetailView?,
    private val model: Contract.Model,
) : Contract.DetailPresenter {

    override fun requestDetails(movieId: Int) {
        model.getMovieDetails(movieId, object : retrofit2.Callback<MovieDetails> {
            override fun onResponse(call: Call<MovieDetails>, response: Response<MovieDetails>) {
                response.body()?.let {
                    view?.onDetailsResponse(it)
                }
            }

            override fun onFailure(call: Call<MovieDetails>, t: Throwable) {}
        })
    }

    override fun requestVideos(movieId: Int) {
        model.getVideos(movieId, object : retrofit2.Callback<VideoResponse> {
            override fun onResponse(call: Call<VideoResponse>, response: Response<VideoResponse>) {
                response.body()?.let {
                    view?.onVideoResponse(it.results)
                }
            }

            override fun onFailure(call: Call<VideoResponse>, t: Throwable) {}
        })
    }

    override fun onDestroy() {
        view = null
    }
}