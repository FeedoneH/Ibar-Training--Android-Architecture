package com.example.moviemvvm.data.model.movie

import com.example.moviemvvm.data.model.movie.Movie
import com.google.gson.annotations.SerializedName

data class MovieList(
    @SerializedName("results")
    val movies: List<Movie>,

    )