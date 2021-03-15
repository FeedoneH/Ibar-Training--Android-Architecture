package com.example.moviemvvm.presentation.di

import android.content.Context
import com.example.moviemvvm.presentation.di.artist.ArtistSubComponent
import com.example.moviemvvm.presentation.di.movie.MovieSubComponent
import com.example.moviemvvm.presentation.di.tvShow.TvShowSubComponent
import dagger.Module
import dagger.Provides
import javax.inject.Singleton


@Module(subcomponents = [MovieSubComponent::class, TvShowSubComponent::class, ArtistSubComponent::class])
class AppModule(private val context: Context) {

    @Singleton
    @Provides
    fun provideAppContext(): Context {
        return context.applicationContext
    }
}