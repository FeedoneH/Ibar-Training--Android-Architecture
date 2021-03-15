package com.example.moviemvvm.presentation.di.tvShow

import com.example.moviemvvm.domain.usecase.GetArtistsUseCase
import com.example.moviemvvm.domain.usecase.GetTVShowsUseCase
import com.example.moviemvvm.domain.usecase.UpdateArtistsUseCase
import com.example.moviemvvm.domain.usecase.UpdateTVShowUseCase
import com.example.moviemvvm.presentation.artist.ArtistsViewModelFactory
import com.example.moviemvvm.presentation.tvShow.TvShowViewModelFactory
import dagger.Module
import dagger.Provides

@Module
class TvShowModule {
    @TvShowScope
    @Provides
    fun provideTvShowViewModelFactory(
        getTvShowUseCase: GetTVShowsUseCase,
        updateTvShowUseCase: UpdateTVShowUseCase
    ): TvShowViewModelFactory {
        return TvShowViewModelFactory(getTvShowUseCase, updateTvShowUseCase)
    }
}