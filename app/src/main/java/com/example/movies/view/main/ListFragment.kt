package com.example.movies.view.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.example.movies.databinding.FragmentListBinding
import com.example.movies.model.data.ApiResponse
import com.example.movies.model.data.Movie
import com.example.movies.model.network.RetrofitService
import com.example.movies.view.adapters.MovieAdapter
import retrofit2.Call
import retrofit2.Response


class ListFragment : Fragment() {

    companion object {
        private const val API_KEY = "032c9cedbfb4a49a4ef35763a4f395b1"
    }

    private var _binding: FragmentListBinding? = null
    private val binding get() = _binding!!

    private val adapter = MovieAdapter()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentListBinding.inflate(inflater, container, false)

        setupRecycler()

        val service = RetrofitService.getService()
        service.getMovies(API_KEY).enqueue(object : retrofit2.Callback<ApiResponse> {
            override fun onResponse(call: Call<ApiResponse>, response: Response<ApiResponse>) {
                response.body()?.let {
                    adapter.movieList = it.results
                }
            }

            override fun onFailure(call: Call<ApiResponse>, t: Throwable) {
                Toast.makeText(requireContext(), t.message, Toast.LENGTH_SHORT).show()
            }
        })

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
}

