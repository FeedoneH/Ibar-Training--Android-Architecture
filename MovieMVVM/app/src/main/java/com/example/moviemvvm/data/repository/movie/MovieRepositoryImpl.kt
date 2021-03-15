package com.example.moviemvvm.data.repository.movie

import android.util.Log
import com.example.moviemvvm.data.model.movie.Movie
import com.example.moviemvvm.data.repository.movie.datasource.MovieCacheDataSource
import com.example.moviemvvm.data.repository.movie.datasource.MovieLocalDataSource
import com.example.moviemvvm.data.repository.movie.datasource.MovieRemoteDataSource
import com.example.moviemvvm.domain.repository.MoviesRepository
import java.lang.Exception

class MovieRepositoryImpl(
    private val movieRemoteDataSource: MovieRemoteDataSource,
    private val movieLocalDataSource: MovieLocalDataSource,
    private val movieCacheDataSource: MovieCacheDataSource
) : MoviesRepository {
    override suspend fun getMovies(): List<Movie>? {
        return getMoviesFromCache()
    }

    override suspend fun updateMovies(): List<Movie>? {
      var updatedMovieList = getMoviesFromAPI()
        movieLocalDataSource.clearAllMovies()
        movieLocalDataSource.saveMoviesToDB(updatedMovieList)
        movieCacheDataSource.saveMoviesToCache(updatedMovieList)
        return  updatedMovieList
    }

    suspend fun getMoviesFromAPI(): List<Movie> {
        lateinit var moviesList: List<Movie>;
        try {
            moviesList= listOf()
            var response = movieRemoteDataSource.getMoviesFromAPI()
            var responseVal = response.body()

            if (responseVal != null) {
                moviesList = responseVal.movies
                Log.i("MyTag", "$moviesList")

            }
        } catch (e: Exception) {
            Log.i("MyTag", e.message.toString())
        }
        return moviesList
    }

    suspend fun getMoviesFromLocalDB(): List<Movie> {
        lateinit var moviesList: List<Movie>;
        try {
            moviesList = movieLocalDataSource.getMoviesFromDB()
        } catch (e: Exception) {
            Log.i("error", "$e")
        }
        if (moviesList.size > 0) {
            return moviesList
        } else {
            moviesList=getMoviesFromAPI()
            movieLocalDataSource.saveMoviesToDB(moviesList)

        }
        return moviesList
    }

    suspend fun getMoviesFromCache(): List<Movie> {
        lateinit var moviesList: List<Movie>
        try {
            moviesList = movieCacheDataSource.getMoviesFromCache()
            if (moviesList.size > 0) {
                return moviesList
            } else {
                moviesList = getMoviesFromLocalDB()
                movieCacheDataSource.saveMoviesToCache(moviesList)
            }
        } catch (e: Exception) {
            Log.i("error", "$e")
        }
        return moviesList
    }
}