package com.example.moviemvvm.data.repository.tvShow.datasource

import com.example.moviemvvm.data.model.tvShow.TvShow

interface TvShowCacheDataSource {

    suspend fun getTvShowsFromCache(): List<TvShow>
    suspend fun saveTvShowsToCache(tvShow: List<TvShow>)
}