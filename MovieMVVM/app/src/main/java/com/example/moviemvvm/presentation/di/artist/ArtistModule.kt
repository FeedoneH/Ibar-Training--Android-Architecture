package com.example.moviemvvm.presentation.di.artist

import com.example.moviemvvm.domain.usecase.GetArtistsUseCase
import com.example.moviemvvm.domain.usecase.UpdateArtistsUseCase
import com.example.moviemvvm.presentation.artist.ArtistsViewModelFactory
import dagger.Module
import dagger.Provides

@Module
class ArtistModule {
    @ArtistScope
    @Provides
    fun provideArtistViewModelFactory(
        getArtistsUseCase: GetArtistsUseCase,
        updateArtistsUseCase: UpdateArtistsUseCase
    ): ArtistsViewModelFactory {
        return ArtistsViewModelFactory(getArtistsUseCase, updateArtistsUseCase)
    }
}