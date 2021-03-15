package com.example.moviemvvm.domain.usecase

import com.example.moviemvvm.data.model.movie.Movie
import com.example.moviemvvm.domain.repository.MoviesRepository

class UpdateMoviesUseCase(private val repository: MoviesRepository) {
suspend fun execute():List<Movie>?=repository.updateMovies()
}