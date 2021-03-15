package com.example.moviemvvm.data.repository.tvShow.datasource

import com.example.moviemvvm.data.model.tvShow.TvShow

interface TvShowLocalDataSource
{
    suspend fun getTvSHowsFromDB():List<TvShow>
    suspend fun saveTvShowsToDB(tvShows: List<TvShow>)
    suspend fun clearAllTvShows()
}