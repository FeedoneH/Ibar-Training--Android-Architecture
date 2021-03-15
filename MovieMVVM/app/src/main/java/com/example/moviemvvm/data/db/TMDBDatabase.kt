package com.example.moviemvvm.data.db

import androidx.room.Database
import androidx.room.DatabaseConfiguration
import androidx.room.InvalidationTracker
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteOpenHelper
import com.example.moviemvvm.data.model.artist.Artist
import com.example.moviemvvm.data.model.movie.Movie
import com.example.moviemvvm.data.model.tvShow.TvShow


@Database(
    entities = [Movie::class, Artist::class, TvShow::class],
    version = 1,
    exportSchema = false
)
abstract class TMDBDatabase : RoomDatabase() {
    abstract fun movieDAO(): MovieDAO
    abstract fun tvShowDAO(): TVShowDAO
    abstract fun artistDAO(): ArtistDAO
}