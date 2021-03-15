package com.example.moviemvvm.data.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.moviemvvm.data.model.tvShow.TvShow


@Dao
interface TVShowDAO {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertTVShow(tvShow: List<TvShow>)

    @Query("Select*from popular_tvShows")
    suspend fun selectAllTVShows():List<TvShow>

    @Query("Delete from popular_tvShows")
    suspend fun deleteAllTVShows()
}