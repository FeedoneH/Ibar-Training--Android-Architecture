package com.example.moviemvvm.presentation.di

import com.example.moviemvvm.domain.repository.ArtistsRepository
import com.example.moviemvvm.domain.repository.MoviesRepository
import com.example.moviemvvm.domain.repository.TVShowsRepository
import com.example.moviemvvm.domain.usecase.*
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class UseCaseModule {

    @Provides
    fun provideGetMoviesUseCase(moviesRepository: MoviesRepository): GetMoviesUseCase {
        return GetMoviesUseCase(moviesRepository)
    }

    @Provides
    fun provideUpdateMoviesUseCase(moviesRepository: MoviesRepository): UpdateMoviesUseCase {
        return UpdateMoviesUseCase(moviesRepository)
    }


    @Provides
    fun provideGetTvShowsUseCase(tvShowsRepository: TVShowsRepository): GetTVShowsUseCase {
        return GetTVShowsUseCase(tvShowsRepository)
    }


    @Provides
    fun provideUpdateTvShowsUseCase(tvShowsRepository: TVShowsRepository): UpdateTVShowUseCase {
        return UpdateTVShowUseCase(tvShowsRepository)
    }



    @Provides
    fun provideGetArtistsUseCase(artistsRepository: ArtistsRepository): GetArtistsUseCase {
        return GetArtistsUseCase(artistsRepository)
    }


    @Provides
    fun provideUpdateArtistsUseCase(artistsRepository: ArtistsRepository): UpdateArtistsUseCase {
        return UpdateArtistsUseCase(artistsRepository)
    }
}