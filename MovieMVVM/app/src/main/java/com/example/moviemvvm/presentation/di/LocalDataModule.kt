package com.example.moviemvvm.presentation.di

import com.example.moviemvvm.data.db.ArtistDAO
import com.example.moviemvvm.data.db.MovieDAO
import com.example.moviemvvm.data.db.TVShowDAO
import com.example.moviemvvm.data.repository.artist.datasource.ArtistLocalDataSource
import com.example.moviemvvm.data.repository.artist.datasourceimplementation.ArtistsLocalDataSourceImpl
import com.example.moviemvvm.data.repository.movie.datasource.MovieLocalDataSource
import com.example.moviemvvm.data.repository.movie.datasourceimplementation.MovieLocalDataSourceImpl
import com.example.moviemvvm.data.repository.tvShow.datasource.TvShowLocalDataSource
import com.example.moviemvvm.data.repository.tvShow.datasourceimplementation.TvShowLocalDataSourceImpl
import dagger.Module
import dagger.Provides
import javax.inject.Singleton


@Module
class LocalDataModule {
    @Singleton
    @Provides
    fun provideMovieLocalDataModule(movieDAO: MovieDAO): MovieLocalDataSource {
        return MovieLocalDataSourceImpl(movieDAO)
    }
    @Singleton
    @Provides
    fun provideArtistLocalDataModule(artistDAO: ArtistDAO): ArtistLocalDataSource {
        return ArtistsLocalDataSourceImpl(artistDAO)
    }
    @Singleton
    @Provides
    fun provideTvShowLocalDataModule(tvShowDAO: TVShowDAO): TvShowLocalDataSource {
        return TvShowLocalDataSourceImpl(tvShowDAO)
    }


}