package com.example.movies.view.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.movies.databinding.VideoItemBinding
import com.example.movies.model.data.Video

class VideoAdapter : RecyclerView.Adapter<VideoAdapter.VideoViewHolder>() {
    var videoList: List<Video> = emptyList()
    set(value) {
        field = value
        notifyDataSetChanged()
    }

    var onVideoClickListener: OnVideoClickListener? = null
    interface OnVideoClickListener {
        fun onVideoClick(videoKey: String?)
    }

    inner class VideoViewHolder(private val binding: VideoItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(video: Video) {
            binding.video = video
            binding.root.setOnClickListener {
                onVideoClickListener?.onVideoClick(videoList[adapterPosition].key)
            }
            binding.executePendingBindings()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VideoViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = VideoItemBinding.inflate(layoutInflater, parent, false)
        return VideoViewHolder(binding)
    }

    override fun getItemCount() = videoList.size

    override fun onBindViewHolder(holder: VideoViewHolder, position: Int) {
        holder.bind(videoList[position])
    }
}
