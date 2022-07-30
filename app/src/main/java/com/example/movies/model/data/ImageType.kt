package com.example.movies.model.data

import com.example.movies.R

enum class ImageType(val size: String, val default: Int) {
    POSTER("w185", R.drawable.default_poster),
    BACKDROP("w780", R.drawable.default_back),
    ACTOR("w185", R.drawable.default_actor)
}