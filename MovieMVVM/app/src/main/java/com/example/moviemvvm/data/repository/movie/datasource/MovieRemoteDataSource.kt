package com.example.moviemvvm.data.repository.movie.datasource
import com.example.moviemvvm.data.model.movie.MovieList
import retrofit2.Response

interface MovieRemoteDataSource {

    suspend fun getMoviesFromAPI():Response<MovieList>
}