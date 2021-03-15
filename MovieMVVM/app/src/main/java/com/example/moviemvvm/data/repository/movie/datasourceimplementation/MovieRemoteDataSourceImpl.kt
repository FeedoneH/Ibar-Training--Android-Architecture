package com.example.moviemvvm.data.repository.movie.datasourceimplementation

import com.example.moviemvvm.data.api.TMBDService

import com.example.moviemvvm.data.model.movie.MovieList
import com.example.moviemvvm.data.repository.movie.datasource.MovieRemoteDataSource
import retrofit2.Response

class MovieRemoteDataSourceImpl(private val tmbdService: TMBDService, private val API_KEY:String) :
    MovieRemoteDataSource {
    override suspend fun getMoviesFromAPI(): Response<MovieList> = tmbdService.getMoviesList(API_KEY)

}