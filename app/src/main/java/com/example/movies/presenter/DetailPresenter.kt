package com.example.movies.presenter

import com.example.movies.Contract
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.schedulers.Schedulers

class DetailPresenter(
    private var view: Contract.DetailView?,
    private val model: Contract.Model,
) : Contract.DetailPresenter {
    private val disposables = CompositeDisposable()

    override fun requestMovieInfo(movieId: Int) {
        disposables.addAll(
            requestDetails(movieId),
            requestCast(movieId),
            requestVideos(movieId)
        )
    }

    private fun requestDetails(movieId: Int) = model.getMovieDetails(movieId)
        .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe { response, _ -> view?.onDetailsResponse(response) }

    private fun requestCast(movieId: Int) = model.getCast(movieId)
        .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe { response, _ -> view?.onCastResponse(response.cast.take(8)) }

    private fun requestVideos(movieId: Int) = model.getVideos(movieId)
        .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe { response, _ -> view?.onVideoResponse(response.results) }

    override fun onDestroy() {
        view = null
        disposables.dispose()
    }
}