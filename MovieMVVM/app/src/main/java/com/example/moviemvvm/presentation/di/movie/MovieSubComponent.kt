package com.example.moviemvvm.presentation.di.movie

import com.example.moviemvvm.presentation.artist.ArtistsFragment
import com.example.moviemvvm.presentation.movie.MoviesFragment
import dagger.Subcomponent

@MovieScope
@Subcomponent(modules = [MovieModule::class])
interface MovieSubComponent {
    fun inject(fragment: MoviesFragment)

    @Subcomponent.Factory
    interface Factory {
        fun create(): MovieSubComponent
    }
}