package com.example.movies.view.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.movies.databinding.MovieItemBinding
import com.example.movies.model.data.Movie

class MovieAdapter : RecyclerView.Adapter<MovieAdapter.MovieViewHolder>() {
    private val movieList: MutableList<Movie> = mutableListOf()

    inner class MovieViewHolder(private val binding: MovieItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(movie: Movie) {
            binding.movie = movie
            binding.executePendingBindings()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = MovieItemBinding.inflate(layoutInflater, parent, false)
        return MovieViewHolder(binding)
    }

    override fun getItemCount() = movieList.size

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        holder.bind(movieList[position])
    }

    fun addMovies(newMovies: List<Movie>) {
        movieList.addAll(newMovies)
        notifyDataSetChanged()
    }

    fun clearMovies() {
        movieList.clear()
    }
}