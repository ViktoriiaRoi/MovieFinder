package com.example.movies.presenter

import com.example.movies.Contract
import com.example.movies.model.data.Sorting
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.Disposable
import io.reactivex.rxjava3.schedulers.Schedulers

class ListPresenter(
    private var view: Contract.ListView?,
    private val model: Contract.Model,
) : Contract.ListPresenter {
    private var disposable: Disposable? = null

    override fun requestMovies(sorting: Sorting, page: Int) {
        disposable = model.getMovies(sorting, page)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                { view?.onMovieResponse(it.results) },
                { view?.onFailure(it) })
    }

    override fun onDestroy() {
        view = null
        disposable?.dispose()
    }
}