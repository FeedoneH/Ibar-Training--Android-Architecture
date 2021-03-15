package com.example.moviemvvm.presentation.di

import com.example.moviemvvm.data.model.artist.Artist
import com.example.moviemvvm.data.repository.artist.datasource.ArtistCacheDataSource
import com.example.moviemvvm.data.repository.artist.datasourceimplementation.ArtistCacheDataSourceImpl
import com.example.moviemvvm.data.repository.movie.datasource.MovieCacheDataSource
import com.example.moviemvvm.data.repository.movie.datasourceimplementation.MovieCacheDataSourceImpl
import com.example.moviemvvm.data.repository.tvShow.datasource.TvShowCacheDataSource
import com.example.moviemvvm.data.repository.tvShow.datasourceimplementation.TvShowCacheDataSourceImpl
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class CacheDataModule
{
    @Singleton
    @Provides
    fun provideMovieCacheDataModule():MovieCacheDataSource{
        return MovieCacheDataSourceImpl()
    }
    @Singleton
    @Provides
    fun provideArtistCacheDataModule(): ArtistCacheDataSource {
        return ArtistCacheDataSourceImpl()
    }
    @Singleton
    @Provides
    fun provideTvShowCacheDataModule():TvShowCacheDataSource{
        return TvShowCacheDataSourceImpl()
    }
}