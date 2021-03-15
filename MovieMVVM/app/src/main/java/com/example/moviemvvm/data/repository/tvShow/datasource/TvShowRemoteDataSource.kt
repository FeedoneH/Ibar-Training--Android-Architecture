package com.example.moviemvvm.data.repository.tvShow.datasource

import com.example.moviemvvm.data.model.tvShow.TVShowList
import retrofit2.Response

interface TvShowRemoteDataSource {
    suspend fun getTvShowsFromAPI():Response<TVShowList>
}