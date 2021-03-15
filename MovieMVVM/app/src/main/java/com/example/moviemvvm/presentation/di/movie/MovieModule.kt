package com.example.moviemvvm.presentation.di.movie

import com.example.moviemvvm.domain.usecase.GetArtistsUseCase
import com.example.moviemvvm.domain.usecase.GetMoviesUseCase
import com.example.moviemvvm.domain.usecase.UpdateArtistsUseCase
import com.example.moviemvvm.domain.usecase.UpdateMoviesUseCase
import com.example.moviemvvm.presentation.artist.ArtistsViewModelFactory
import com.example.moviemvvm.presentation.movie.MovieViewModelFactory
import dagger.Module
import dagger.Provides

@Module
class MovieModule {
    @MovieScope
    @Provides
    fun provideMovieViewModelFactory(
        getMoviesUseCase: GetMoviesUseCase,
        updateMoviesUseCase: UpdateMoviesUseCase
    ): MovieViewModelFactory {
        return MovieViewModelFactory(getMoviesUseCase, updateMoviesUseCase)
    }
}