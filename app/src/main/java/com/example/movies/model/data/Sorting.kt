package com.example.movies.model.data

enum class Sorting(val title: String, val queryParam: String, val voteCount: String) {
    POPULARITY("Popular", "popularity.desc", "1000"),
    TOP_RATING("Top rated", "vote_average.desc", "5000"),
    LATEST_DATE("Latest", "release_date.desc", "500")
}