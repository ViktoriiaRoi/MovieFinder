package com.example.movies.presenter

import com.example.movies.Contract
import com.example.movies.model.data.Sorting
import dagger.hilt.components.SingletonComponent
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.Disposable
import io.reactivex.rxjava3.schedulers.Schedulers
import it.czerwinski.android.hilt.annotations.BoundTo
import javax.inject.Inject

@BoundTo(supertype = Contract.ListPresenter::class, component = SingletonComponent::class)
class ListPresenter @Inject constructor(val model: Contract.Model) : Contract.ListPresenter {
    private var disposable: Disposable? = null
    private var view: Contract.ListView? = null

    override fun requestMovies(sorting: Sorting, page: Int) {
        disposable = model.getMovies(sorting, page)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                { view?.onMovieResponse(it.results) },
                { view?.onFailure(it) })
    }

    override fun attachView(listView: Contract.ListView) {
        view = listView
    }

    override fun onDestroy() {
        view = null
        disposable?.dispose()
    }
}