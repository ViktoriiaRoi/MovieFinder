package com.example.movies.view.adapters

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.example.movies.model.data.PosterSize
import com.squareup.picasso.Picasso

class BindingAdapters {
    companion object {
        private const val BASE_POSTER_URL = "https://image.tmdb.org/t/p/"

        @BindingAdapter("bind:posterUrl", "bind:posterSize")
        @JvmStatic
        fun loadPoster(view: ImageView, url: String, size: PosterSize) {
            Picasso.get().load(BASE_POSTER_URL + size.value + url).into(view)
        }
    }
}