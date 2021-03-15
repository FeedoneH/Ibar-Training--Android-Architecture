package com.example.moviemvvm.data.repository.tvShow.datasourceimplementation

import com.example.moviemvvm.data.db.TVShowDAO
import com.example.moviemvvm.data.model.tvShow.TvShow
import com.example.moviemvvm.data.repository.tvShow.datasource.TvShowLocalDataSource
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class TvShowLocalDataSourceImpl(private val tvShowDAO: TVShowDAO) : TvShowLocalDataSource {
    override suspend fun getTvSHowsFromDB(): List<TvShow> {
        return tvShowDAO.selectAllTVShows()
    }

    override suspend fun saveTvShowsToDB(tvShows: List<TvShow>) {
        CoroutineScope(Dispatchers.IO).launch {
            tvShowDAO.insertTVShow(tvShows)
        }
    }

    override suspend fun clearAllTvShows() {
        CoroutineScope(Dispatchers.IO).launch {
            tvShowDAO.deleteAllTVShows()
        }
    }
}