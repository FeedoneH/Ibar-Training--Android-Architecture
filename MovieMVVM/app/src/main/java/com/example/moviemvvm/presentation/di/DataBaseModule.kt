package com.example.moviemvvm.presentation.di

import android.content.Context
import androidx.room.Room
import com.example.moviemvvm.data.db.ArtistDAO
import com.example.moviemvvm.data.db.MovieDAO
import com.example.moviemvvm.data.db.TMDBDatabase
import com.example.moviemvvm.data.db.TVShowDAO
import dagger.Module
import dagger.Provides
import javax.inject.Singleton


@Module
class DataBaseModule {
    @Singleton
    @Provides
    fun provideDatabase(context: Context): TMDBDatabase {
        return Room.databaseBuilder(context, TMDBDatabase::class.java, "tmdbclient").build()
    }

    @Singleton
    @Provides
    fun provideMovieDao(tmdbDatabase: TMDBDatabase): MovieDAO {
        return tmdbDatabase.movieDAO()
    }

    @Singleton
    @Provides
    fun provideTVShowDao(tmdbDatabase: TMDBDatabase): TVShowDAO {
        return tmdbDatabase.tvShowDAO()
    }

    @Singleton
    @Provides
    fun provideArtistDao(tmdbDatabase: TMDBDatabase): ArtistDAO {
        return tmdbDatabase.artistDAO()
    }

}