package com.example.moviemvvm.domain.repository

import com.example.moviemvvm.data.model.movie.Movie

interface MoviesRepository {
    suspend fun getMovies():List<Movie>?
    suspend fun updateMovies():List<Movie>?
}