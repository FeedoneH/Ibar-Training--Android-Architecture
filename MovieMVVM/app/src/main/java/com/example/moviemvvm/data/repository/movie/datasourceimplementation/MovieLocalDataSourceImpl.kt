package com.example.moviemvvm.data.repository.movie.datasourceimplementation

import com.example.moviemvvm.data.db.MovieDAO
import com.example.moviemvvm.data.model.movie.Movie
import com.example.moviemvvm.data.repository.movie.datasource.MovieLocalDataSource
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MovieLocalDataSourceImpl(private val movieDAO: MovieDAO) :
    MovieLocalDataSource {

    override suspend fun getMoviesFromDB(): List<Movie> {
        return movieDAO.selectAllMovies()
    }

    override suspend fun saveMoviesToDB(movies: List<Movie>) {
        CoroutineScope(Dispatchers.IO).launch {
            movieDAO.insertMovie(movies)
        }

    }

    override suspend fun clearAllMovies() {
        CoroutineScope(Dispatchers.IO).launch {
            movieDAO.deleteAllMovies()
        }

    }
}