package com.example.movies.model.data

enum class Sorting(val title: String, val queryParam: String) {
    POPULARITY("Popular", "popularity.desc"),
    TOP_RATING("Top rated", "vote_average.desc")
}