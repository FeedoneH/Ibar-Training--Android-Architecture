package com.example.moviemvvm.data.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.moviemvvm.data.model.movie.Movie

@Dao
interface MovieDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMovie(movies: List<Movie>)

    @Query("Select * from popular_movies")
    suspend fun selectAllMovies():List<Movie>

    @Query("Delete from popular_movies")
    suspend fun deleteAllMovies()

}