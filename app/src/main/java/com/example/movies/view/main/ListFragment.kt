package com.example.movies.view.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.example.movies.Contract
import com.example.movies.databinding.FragmentListBinding
import com.example.movies.model.Model
import com.example.movies.model.data.Movie
import com.example.movies.presenter.Presenter
import com.example.movies.view.adapters.MovieAdapter


class ListFragment : Fragment(), Contract.View {

    private var _binding: FragmentListBinding? = null
    private val binding get() = _binding!!

    private var presenter: Contract.Presenter? = null
    private val adapter = MovieAdapter()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentListBinding.inflate(inflater, container, false)

        setupRecycler()
        presenter = Presenter(this, Model())
        presenter?.requestData()

        return binding.root
    }

    private fun setupRecycler() {
        val recycler = binding.movieRecycler
        recycler.layoutManager = GridLayoutManager(requireContext(), 2)
        recycler.adapter = adapter

        adapter.onPosterClickListener = object : MovieAdapter.OnPosterClickListener {
            override fun onPosterClick(movie: Movie) {
                val action = MainFragmentDirections.actionMainFragmentToDetailFragment(movie)
                findNavController().navigate(action)
            }
        }
    }

    override fun onResponse(movies: List<Movie>) {
        adapter.movieList = movies
    }

    override fun onFailure(t: Throwable) {
        Toast.makeText(requireContext(), t.message, Toast.LENGTH_SHORT).show()
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter!!.onDestroy()
    }
}

