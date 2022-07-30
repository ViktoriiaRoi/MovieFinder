package com.example.movies

import com.example.movies.model.data.*

interface Contract {
    interface Model {
        fun getMovies(sorting: Sorting, page: Int, callback: retrofit2.Callback<MovieResponse>)
        fun getMovieDetails(movieId: Int, callback: retrofit2.Callback<MovieDetails>)
        fun getVideos(movieId: Int, callback: retrofit2.Callback<VideoResponse>)
    }

    interface ListView {
        fun onMovieResponse(movies: List<Movie>)
        fun onFailure(t: Throwable)
    }

    interface DetailView {
        fun onDetailsResponse(details: MovieDetails)
        fun onVideoResponse(videos: List<Video>)
    }

    interface BasePresenter {
        fun onDestroy()
    }

    interface ListPresenter : BasePresenter {
        fun requestMovies(sorting: Sorting, page: Int)
    }

    interface DetailPresenter : BasePresenter {
        fun requestDetails(movieId: Int)
        fun requestVideos(movieId: Int)
    }
}