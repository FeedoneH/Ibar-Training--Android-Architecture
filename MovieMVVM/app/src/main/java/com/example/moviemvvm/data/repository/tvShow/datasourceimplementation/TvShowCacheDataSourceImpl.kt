package com.example.moviemvvm.data.repository.tvShow.datasourceimplementation

import com.example.moviemvvm.data.model.tvShow.TVShowList
import com.example.moviemvvm.data.model.tvShow.TvShow
import com.example.moviemvvm.data.repository.tvShow.datasource.TvShowCacheDataSource

class TvShowCacheDataSourceImpl: TvShowCacheDataSource {
    private var tvShowsList = ArrayList<TvShow>()
    override suspend fun getTvShowsFromCache(): List<TvShow> {
       return tvShowsList
    }


    override suspend fun saveTvShowsToCache(tvShow: List<TvShow>) {
      tvShowsList.clear()
        tvShowsList= ArrayList(tvShow)

    }
}