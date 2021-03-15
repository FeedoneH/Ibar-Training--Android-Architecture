package com.example.moviemvvm.presentation.di

import com.example.moviemvvm.presentation.di.artist.ArtistSubComponent
import com.example.moviemvvm.presentation.di.movie.MovieSubComponent
import com.example.moviemvvm.presentation.di.tvShow.TvShowSubComponent

interface Injector {
    fun createmovieSubComponent(): MovieSubComponent
    fun createartistSubComponent(): ArtistSubComponent
    fun createtvShowSubComponent(): TvShowSubComponent
}