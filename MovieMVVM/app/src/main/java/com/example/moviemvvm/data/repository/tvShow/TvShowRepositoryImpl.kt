package com.example.moviemvvm.data.repository.tvShow


import android.util.Log
import com.example.moviemvvm.data.model.tvShow.TvShow
import com.example.moviemvvm.data.repository.tvShow.datasource.TvShowCacheDataSource
import com.example.moviemvvm.data.repository.tvShow.datasource.TvShowLocalDataSource
import com.example.moviemvvm.data.repository.tvShow.datasource.TvShowRemoteDataSource
import com.example.moviemvvm.domain.repository.TVShowsRepository
import java.lang.Exception

class TvShowRepositoryImpl(
    private val tvShowLocalDataSource: TvShowLocalDataSource,
    private val tvShowRemoteDataSource: TvShowRemoteDataSource,
    private val tvShowCacheDataSource: TvShowCacheDataSource
) : TVShowsRepository {
    override suspend fun getTVShows(): List<TvShow>? {
        return getTvShowsFromCache()
    }

    override suspend fun updateTVShows(): List<TvShow>? {
        lateinit var updatedTvShows: List<TvShow>
        updatedTvShows = getTvShowsFromAPI()
        tvShowLocalDataSource.clearAllTvShows()
        tvShowLocalDataSource.saveTvShowsToDB(updatedTvShows)
        tvShowCacheDataSource.saveTvShowsToCache(updatedTvShows)
        return updatedTvShows
    }

    suspend fun getTvShowsFromAPI(): List<TvShow> {
        lateinit var tvShowList: List<TvShow>
        try {
            tvShowList = listOf()
            var response = tvShowRemoteDataSource.getTvShowsFromAPI()
            var body = response.body()
            if (body != null) {
                tvShowList = body.tvShow
            }
        } catch (e: Exception) {
            Log.i(" local error", "${e.message}")
        }
        return tvShowList
    }

    suspend fun getTvShowsFromDB(): List<TvShow> {
        lateinit var tvShowList: List<TvShow>

        try {
            tvShowList= listOf()
            tvShowList = tvShowLocalDataSource.getTvSHowsFromDB()
            if (tvShowList.size > 0) {
                return tvShowList
            } else {
                tvShowList = getTvShowsFromAPI()
                tvShowLocalDataSource.saveTvShowsToDB(tvShowList)
            }
        } catch (e: Exception) {
            Log.i("db error", "${e.message}")
        }

        return tvShowList
    }

    suspend fun getTvShowsFromCache(): List<TvShow> {
        lateinit var tvShowList: List<TvShow>
        try {
            tvShowList= listOf()
            tvShowList = tvShowCacheDataSource.getTvShowsFromCache()
            if (tvShowList.size > 0) {
                return tvShowList
            } else {
                tvShowList = getTvShowsFromDB()
                tvShowCacheDataSource.saveTvShowsToCache(tvShowList)
            }
        } catch (e: Exception) {
            Log.i(" cache error", "${e.message}")
        }
        return tvShowList
    }
}