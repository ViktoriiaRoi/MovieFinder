# Movie Finder
This is an android application for finding movies to watch.

## Features
* There are 3 lists of movies: "Popular", "Top rated" and "Latest".
* By clicking on a movie, you can see detailed information: rating, year, duration, genres, and overview. 
* Also there is a list of actors with photos and a list of trailers (click on it to watch video on YouTube).

## Implementation
This app is designed as MVP architecture pattern

* __Dagger Hilt__ - to implement dependency injection

### Model
 * __Retrofit2__ - to get data from api

### Presenter
* __RxJava3__ - to perform asynchronous api requests

### View
* __Picasso__ - to show picture by url
* __ViewPager2__ - to slide screens
* __SwipeRefreshLayout__
* __Navigation component__ - to navigate between fragments
* __Data binding__ - UI interaction

## Demo
<img src="demo/movie_demo.gif"/>
