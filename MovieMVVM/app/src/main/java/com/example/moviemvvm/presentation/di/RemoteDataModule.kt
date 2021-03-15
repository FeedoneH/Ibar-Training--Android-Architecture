package com.example.moviemvvm.presentation.di

import com.example.moviemvvm.data.api.TMBDService
import com.example.moviemvvm.data.repository.artist.datasource.ArtistRemoteDataSource
import com.example.moviemvvm.data.repository.artist.datasourceimplementation.ArtistRemoteDatSourceImpl
import com.example.moviemvvm.data.repository.movie.datasource.MovieRemoteDataSource
import com.example.moviemvvm.data.repository.movie.datasourceimplementation.MovieRemoteDataSourceImpl
import com.example.moviemvvm.data.repository.tvShow.datasource.TvShowRemoteDataSource
import com.example.moviemvvm.data.repository.tvShow.datasourceimplementation.TvShowRemoteDataSourceImpl
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class RemoteDataModule(private val api_key:String) {
    @Singleton
    @Provides
    fun provideMovieRemoteDataSource(tmbdService: TMBDService):MovieRemoteDataSource{
        return MovieRemoteDataSourceImpl(tmbdService,api_key)
    }
    @Singleton
    @Provides
    fun provideArtistRemoteDataSource(tmbdService: TMBDService): ArtistRemoteDataSource {
        return ArtistRemoteDatSourceImpl(tmbdService,api_key)
    }
    @Singleton
    @Provides
    fun provideTvShowRemoteDataSource(tmbdService: TMBDService): TvShowRemoteDataSource {
        return TvShowRemoteDataSourceImpl(tmbdService,api_key)
    }
}