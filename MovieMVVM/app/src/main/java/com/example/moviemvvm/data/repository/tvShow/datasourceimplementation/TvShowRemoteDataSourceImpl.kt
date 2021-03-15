package com.example.moviemvvm.data.repository.tvShow.datasourceimplementation

import com.example.moviemvvm.data.api.TMBDService
import com.example.moviemvvm.data.model.tvShow.TVShowList
import com.example.moviemvvm.data.repository.tvShow.datasource.TvShowRemoteDataSource
import retrofit2.Response

class TvShowRemoteDataSourceImpl(
    private val tmbdService: TMBDService,
    private val API_KEY: String
) : TvShowRemoteDataSource {
    override suspend fun getTvShowsFromAPI(): Response<TVShowList> =
        tmbdService.getTVShowList(API_KEY)

}