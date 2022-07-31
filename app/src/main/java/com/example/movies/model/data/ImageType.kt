package com.example.movies.model.data

import com.example.movies.R

enum class ImageType(val size: String, val default: Int) {

    POSTER("w185", R.drawable.default_poster),
    BACKDROP("w780", R.drawable.default_back),
    ACTOR("w185", R.drawable.default_actor),
    THUMBNAIL("mqdefault", R.drawable.default_thumbnail);

    fun getUrl(path: String) =
        if (this == THUMBNAIL) "https://img.youtube.com/vi/$path/$size.jpg"
        else "https://image.tmdb.org/t/p/$size$path"
}