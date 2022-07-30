package com.example.movies.view.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.movies.databinding.ActorItemBinding
import com.example.movies.databinding.VideoItemBinding
import com.example.movies.model.data.Actor
import com.example.movies.model.data.Video

class ActorAdapter(private val actors: List<Actor>) : RecyclerView.Adapter<ActorAdapter.ActorViewHolder>() {

    inner class ActorViewHolder(private val binding: ActorItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(actor: Actor) {
            binding.actor = actor
            binding.executePendingBindings()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ActorViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = ActorItemBinding.inflate(layoutInflater, parent, false)
        return ActorViewHolder(binding)
    }

    override fun getItemCount() = actors.size

    override fun onBindViewHolder(holder: ActorViewHolder, position: Int) {
        holder.bind(actors[position])
    }
}
