package com.example.movies.view.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.movies.databinding.GenreItemBinding
import com.example.movies.databinding.VideoItemBinding
import com.example.movies.model.data.Genre
import com.example.movies.model.data.Video

class GenreAdapter(private val genres: List<Genre>) : RecyclerView.Adapter<GenreAdapter.GenreViewHolder>() {

    inner class GenreViewHolder(private val binding: GenreItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(genre: Genre) {
            binding.genre = genre
            binding.executePendingBindings()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GenreViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = GenreItemBinding.inflate(layoutInflater, parent, false)
        return GenreViewHolder(binding)
    }

    override fun getItemCount() = genres.size

    override fun onBindViewHolder(holder: GenreViewHolder, position: Int) {
        holder.bind(genres[position])
    }
}
