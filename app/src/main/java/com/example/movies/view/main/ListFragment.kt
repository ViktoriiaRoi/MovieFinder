package com.example.movies.view.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.ObservableBoolean
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.movies.Contract
import com.example.movies.databinding.FragmentListBinding
import com.example.movies.model.Model
import com.example.movies.model.data.Movie
import com.example.movies.model.data.Sorting
import com.example.movies.presenter.Presenter
import com.example.movies.view.adapters.MovieAdapter


class ListFragment(val sorting: Sorting) : Fragment(), Contract.View {

    private var _binding: FragmentListBinding? = null
    private val binding get() = _binding!!

    private var presenter: Contract.Presenter? = null
    private val adapter = MovieAdapter()

    private var pageNumber = 1
    private var isLoading = ObservableBoolean(false)

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentListBinding.inflate(inflater, container, false)
        binding.isLoading = isLoading

        setupRecycler()
        presenter = Presenter(this, Model())
        presenter?.requestData(sorting, pageNumber)

        return binding.root
    }

    private fun setupRecycler() {
        val recycler = binding.movieRecycler
        recycler.layoutManager = GridLayoutManager(requireContext(), 2)
        recycler.adapter = adapter

        recycler.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                val layoutManager = LinearLayoutManager::class.java.cast(recyclerView.layoutManager)
                val totalItemCount = layoutManager?.itemCount ?: 0
                val lastVisible = layoutManager?.findLastVisibleItemPosition() ?: 0

                if ((totalItemCount >= 20) and (lastVisible > totalItemCount - 4)) {
                    if (!isLoading.get()) {
                        isLoading.set(true)
                        isLoading.notifyChange()
                        presenter?.requestData(sorting, pageNumber)
                    }
                }
            }
        })

        adapter.onPosterClickListener = object : MovieAdapter.OnPosterClickListener {
            override fun onPosterClick(movie: Movie) {
                val action = MainFragmentDirections.actionMainFragmentToDetailFragment(movie)
                findNavController().navigate(action)
            }
        }
    }

    override fun onResponse(movies: List<Movie>) {
        adapter.addMovies(movies)
        pageNumber++
        isLoading.set(false)
        isLoading.notifyChange()
    }

    override fun onFailure(t: Throwable) {
        Toast.makeText(requireContext(), t.message, Toast.LENGTH_SHORT).show()
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter!!.onDestroy()
    }
}

