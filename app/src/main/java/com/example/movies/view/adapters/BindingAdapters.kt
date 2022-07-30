package com.example.movies.view.adapters

import android.view.View
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.example.movies.model.data.ImageType
import com.squareup.picasso.Picasso

class BindingAdapters {
    companion object {
        private const val BASE_POSTER_URL = "https://image.tmdb.org/t/p/"

        @BindingAdapter("android:imageUrl", "android:imageType")
        @JvmStatic
        fun loadImage(view: ImageView, url: String?, imageType: ImageType) {
            Picasso.get().load(BASE_POSTER_URL + imageType.size + url)
                .placeholder(imageType.default).into(view)
        }

        @BindingAdapter("android:showView")
        @JvmStatic
        fun showView(view: View, show: Boolean) {
            view.visibility = if (show) View.VISIBLE else View.GONE
        }
    }
}