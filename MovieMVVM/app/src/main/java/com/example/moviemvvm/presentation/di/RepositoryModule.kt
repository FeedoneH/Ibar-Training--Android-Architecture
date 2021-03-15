package com.example.moviemvvm.presentation.di

import com.example.moviemvvm.data.repository.artist.ArtistRepositoryImpl
import com.example.moviemvvm.data.repository.artist.datasource.ArtistCacheDataSource
import com.example.moviemvvm.data.repository.artist.datasource.ArtistLocalDataSource
import com.example.moviemvvm.data.repository.artist.datasource.ArtistRemoteDataSource
import com.example.moviemvvm.data.repository.movie.MovieRepositoryImpl
import com.example.moviemvvm.data.repository.movie.datasource.MovieCacheDataSource
import com.example.moviemvvm.data.repository.movie.datasource.MovieLocalDataSource
import com.example.moviemvvm.data.repository.movie.datasource.MovieRemoteDataSource
import com.example.moviemvvm.data.repository.tvShow.TvShowRepositoryImpl
import com.example.moviemvvm.data.repository.tvShow.datasource.TvShowCacheDataSource
import com.example.moviemvvm.data.repository.tvShow.datasource.TvShowLocalDataSource
import com.example.moviemvvm.data.repository.tvShow.datasource.TvShowRemoteDataSource
import com.example.moviemvvm.domain.repository.ArtistsRepository
import com.example.moviemvvm.domain.repository.MoviesRepository
import com.example.moviemvvm.domain.repository.TVShowsRepository
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class RepositoryModule {
    @Provides
    @Singleton
    fun provideMovieRepository(
        movieRemoteDataSource: MovieRemoteDataSource,
        movieLocalDataSource: MovieLocalDataSource,
        movieCacheDataSource: MovieCacheDataSource
    ): MoviesRepository {
        return MovieRepositoryImpl(
            movieRemoteDataSource,
            movieLocalDataSource,
            movieCacheDataSource
        )
    }

    @Provides
    @Singleton
    fun provideArtistRepository(
        artistRemoteDataSource: ArtistRemoteDataSource,
        artistLocalDataSource: ArtistLocalDataSource,
        artistCacheDataSource: ArtistCacheDataSource
    ): ArtistsRepository {
        return ArtistRepositoryImpl(
            artistLocalDataSource,
            artistCacheDataSource,
            artistRemoteDataSource,
            )
    }

    @Provides
    @Singleton
    fun provideTvShowRepository(
        tvShowRemoteDataSource: TvShowRemoteDataSource,
        tvShowLocalDataSource: TvShowLocalDataSource,
        tvShowCacheDataSource: TvShowCacheDataSource
    ): TVShowsRepository {
        return TvShowRepositoryImpl(
            tvShowLocalDataSource,
            tvShowRemoteDataSource,
            tvShowCacheDataSource
        )
    }
}