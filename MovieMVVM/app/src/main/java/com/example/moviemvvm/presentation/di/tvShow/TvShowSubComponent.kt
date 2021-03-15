package com.example.moviemvvm.presentation.di.tvShow

import com.example.moviemvvm.presentation.artist.ArtistsFragment
import com.example.moviemvvm.presentation.tvShow.TvShowsFragment
import dagger.Subcomponent

@TvShowScope
@Subcomponent(modules = [TvShowModule::class])
interface TvShowSubComponent {
    fun inject(fragment: TvShowsFragment)

    @Subcomponent.Factory
    interface Factory {
        fun create(): TvShowSubComponent
    }
}