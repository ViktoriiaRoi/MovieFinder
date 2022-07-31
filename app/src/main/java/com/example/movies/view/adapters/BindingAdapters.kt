package com.example.movies.view.adapters

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import androidx.core.content.ContextCompat.startActivity
import androidx.databinding.BindingAdapter
import com.example.movies.model.data.ImageType
import com.squareup.picasso.Picasso

class BindingAdapters {
    companion object {
        private const val BASE_YOUTUBE_URL = "https://www.youtube.com/watch?v="

        @BindingAdapter("android:imagePath", "android:imageType")
        @JvmStatic
        fun loadImage(view: ImageView, path: String?, imageType: ImageType) {
            if (path == null) return
            Picasso.get().load(imageType.getUrl(path))
                .placeholder(imageType.default).into(view)
        }

        @BindingAdapter("android:showView")
        @JvmStatic
        fun showView(view: View, show: Boolean) {
            view.visibility = if (show) View.VISIBLE else View.GONE
        }

        @BindingAdapter("android:openYoutube")
        @JvmStatic
        fun openYoutube(view: View, videoKey: String?) {
            view.setOnClickListener {
                val url = BASE_YOUTUBE_URL + videoKey
                startActivity(view.context,
                    Intent(Intent.ACTION_VIEW, Uri.parse(url)),
                    Bundle.EMPTY)
            }
        }

    }
}