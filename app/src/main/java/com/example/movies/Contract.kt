package com.example.movies

import com.example.movies.model.data.*

interface Contract {
    interface Model {
        fun getMovies(sorting: Sorting, page: Int, callback: retrofit2.Callback<MovieResponse>)
        fun getVideos(movieId: Int, callback: retrofit2.Callback<VideoResponse>)
    }

    interface BaseView {
        fun onFailure(t: Throwable)
    }

    interface ListView : BaseView {
        fun onMovieResponse(movies: List<Movie>)
    }

    interface DetailView : BaseView {
        fun onVideoResponse(videos: List<Video>)
    }

    interface BasePresenter {
        fun onDestroy()
    }

    interface ListPresenter : BasePresenter {
        fun requestMovies(sorting: Sorting, page: Int)
    }

    interface DetailPresenter : BasePresenter {
        fun requestVideos(movieId: Int)
    }
}