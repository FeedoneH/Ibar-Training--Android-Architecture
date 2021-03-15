package com.example.moviemvvm.domain.usecase

import com.example.moviemvvm.data.model.movie.Movie
import com.example.moviemvvm.domain.repository.MoviesRepository

class GetMoviesUseCase(private val moviesRepository: MoviesRepository){
    suspend fun execute():List<Movie>? = moviesRepository.getMovies()
}