package com.example.movies

import com.example.movies.model.data.*
import io.reactivex.rxjava3.core.Single

interface Contract {
    interface Model {
        fun getMovies(sorting: Sorting, page: Int): Single<MovieResponse>
        fun getMovieDetails(movieId: Int): Single<MovieDetails>
        fun getCast(movieId: Int): Single<CastResponse>
        fun getVideos(movieId: Int): Single<VideoResponse>
    }

    interface ListView {
        fun onMovieResponse(movies: List<Movie>)
        fun onFailure(t: Throwable)
    }

    interface DetailView {
        fun onDetailsResponse(details: MovieDetails)
        fun onCastResponse(actors: List<Actor>)
        fun onVideoResponse(videos: List<Video>)
    }

    interface BasePresenter {
        fun onDestroy()
    }

    interface ListPresenter : BasePresenter {
        fun requestMovies(sorting: Sorting, page: Int)
        fun attachView(listView: ListView)
    }

    interface DetailPresenter : BasePresenter {
        fun requestMovieInfo(movieId: Int)
        fun attachView(detailView: DetailView)
    }
}