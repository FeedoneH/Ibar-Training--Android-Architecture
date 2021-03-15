package com.example.moviemvvm.presentation.di.artist

import com.example.moviemvvm.presentation.artist.ArtistsFragment
import dagger.Subcomponent

@ArtistScope
@Subcomponent(modules = [ArtistModule::class])
interface ArtistSubComponent {
    fun inject(fragment: ArtistsFragment)

    @Subcomponent.Factory
    interface Factory {
        fun create(): ArtistSubComponent
    }
}