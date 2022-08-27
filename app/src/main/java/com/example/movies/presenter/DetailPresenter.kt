package com.example.movies.presenter

import com.example.movies.Contract
import dagger.hilt.components.SingletonComponent
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.schedulers.Schedulers
import it.czerwinski.android.hilt.annotations.BoundTo
import javax.inject.Inject

@BoundTo(supertype = Contract.DetailPresenter::class, component = SingletonComponent::class)
class DetailPresenter @Inject constructor(val model: Contract.Model) : Contract.DetailPresenter {
    private val disposables = CompositeDisposable()
    private var view: Contract.DetailView? = null

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

    override fun attachView(detailView: Contract.DetailView) {
        view = detailView
    }

    override fun onDestroy() {
        view = null
        disposables.dispose()
    }
}