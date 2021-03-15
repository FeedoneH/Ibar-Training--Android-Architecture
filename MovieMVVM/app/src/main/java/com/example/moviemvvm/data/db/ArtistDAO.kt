package com.example.moviemvvm.data.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.moviemvvm.data.model.artist.Artist


@Dao
interface ArtistDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertArtist(artist: List<Artist>)

    @Query("Select * from popular_artists")
    suspend fun selectAllArtists():List<Artist>

    @Query("Delete from popular_artists")
    suspend fun deleteAllMovies()

}